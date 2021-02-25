package org.shazhi.businessEnglishMicroCourse.controller;

import org.apache.catalina.connector.ClientAbortException;
import org.shazhi.businessEnglishMicroCourse.entity.CoursewareEntity;
import org.shazhi.businessEnglishMicroCourse.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

@RestController
public class FileController {
    @Value("${file.save.path}")
    String path;

    final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping("file/{type}/upload")
    public Object uploadFile(@Param("file") MultipartFile file, @PathVariable String type) {
        if (file.isEmpty()) return false;
        String fileName = "hashcode" + '_' + file.getOriginalFilename();
        String hashPath = '\\' + type + '\\' + file.getOriginalFilename().split(".part.")[0] + '\\';
        File fullPath = new File(path + hashPath);
        if (!fullPath.exists()) fullPath.mkdir();
        try {
            file.transferTo(new File(path + hashPath + fileName));
            return hashPath + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("file/validateMd5")
    public Boolean validateMd5(@RequestBody CoursewareEntity courseware) {
        return fileService.findCourseware(courseware).size() == 0;
    }

    @RequestMapping("file/{type}/download/{coursewareId}")
    public void download(HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String type, @PathVariable Integer coursewareId) {
        String range = request.getHeader("Range");
        String key = "courseware:" + coursewareId;
        CoursewareEntity coursewareEntity = (CoursewareEntity) session.getAttribute(key);
        if (coursewareEntity == null) {
            try {
                coursewareEntity = fileService.findCoursewareById(coursewareId);
            } catch (Exception err) {
                System.out.println("不存在该课件");
                coursewareEntity = null;
            }
        }

        if (coursewareEntity == null) {
            // todo
            return;
        }

        long startByte = 0;
        long endByte = coursewareEntity.getTotalSize() - 1;
        if (range != null && range.contains("bytes=") && range.contains("-")) {
            range = range.substring(range.lastIndexOf("=") + 1).trim();
            String[] ranges = range.split("-");
            try {
                //判断range的类型
                if (ranges.length == 1) {
                    //类型一：bytes=-2343
                    if (range.startsWith("-")) {
                        endByte = Long.parseLong(ranges[0]);
                    }
                    //类型二：bytes=2343-
                    else if (range.endsWith("-")) {
                        startByte = Long.parseLong(ranges[0]);
                    }
                }
                //类型三：bytes=22-2343
                else if (ranges.length == 2) {
                    startByte = Long.parseLong(ranges[0]);
                    endByte = Long.parseLong(ranges[1]);
                }

            } catch (NumberFormatException e) {
                startByte = 0;
                endByte = coursewareEntity.getFragmentSize() - 1;
            }

        }

        Long currentPartIndex = startByte / coursewareEntity.getFragmentSize();
        if (endByte >= (currentPartIndex + 1) * coursewareEntity.getFragmentSize()) {
            endByte = (currentPartIndex + 1) * coursewareEntity.getFragmentSize() - 1;
        }

        //要下载的长度
        long contentLength = endByte - startByte + 1;

        // currentFile
        File file = new File(path + coursewareEntity.getCoursewarePath() + "\\" + coursewareEntity.getCoursewareName() + ".part." + currentPartIndex);

        //文件名
        String fileName = coursewareEntity.getCoursewareName();
        //文件类型
        String contentType = request.getServletContext().getMimeType(fileName);

        byte[] fileNameBytes = fileName.getBytes(StandardCharsets.UTF_8);
        fileName = new String(fileNameBytes, 0, fileNameBytes.length, StandardCharsets.ISO_8859_1);

        //各种响应头设置
        //支持断点续传，获取部分字节内容：
        response.setHeader("Accept-Ranges", "bytes");
        //http状态码要为206：表示获取部分内容
        response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
        response.setContentType(contentType);
        response.setHeader("Content-Type", contentType);
        //inline表示浏览器直接使用，attachment表示下载，fileName表示下载的文件名
        if (type.equals("attachment")) {
            response.setHeader("Content-Disposition", "attachment;filename=" + coursewareEntity.getCoursewareName());
        } else {
            response.setHeader("Content-Disposition", "inline;filename=" + coursewareEntity.getCoursewareName());
        }
        response.setHeader("Content-Length", String.valueOf(contentLength));
        // Content-Range，格式为：[要下载的开始位置]-[结束位置]/[文件总大小]
        response.setHeader("Content-Range", "bytes " + startByte + "-" + endByte + "/" + coursewareEntity.getTotalSize());

        BufferedOutputStream outputStream = null;
        //已传送数据大小
        long transmitted = 0;
        startByte %= coursewareEntity.getFragmentSize();
        endByte %= coursewareEntity.getFragmentSize();

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            outputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[4096];
            int len = 0;
            randomAccessFile.seek(startByte);
            //坑爹地方四：判断是否到了最后不足4096（buff的length）个byte这个逻辑（(transmitted + len) <= contentLength）要放前面！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
            //不然会会先读取randomAccessFile，造成后面读取位置出错，找了一天才发现问题所在
            while ((transmitted + len) <= contentLength && (len = randomAccessFile.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
                transmitted += len;
            }
            //处理不足buff.length部分
            if (transmitted < contentLength) {
                len = randomAccessFile.read(buff, 0, (int) (contentLength - transmitted));
                outputStream.write(buff, 0, len);
                transmitted += len;
            }

            outputStream.flush();
            response.flushBuffer();
        } catch (ClientAbortException e) {
            System.out.println("断开链接");
        } catch (IOException e) {
            //捕获此异常表示用户停止下载
            e.printStackTrace();
        }
    }
}
