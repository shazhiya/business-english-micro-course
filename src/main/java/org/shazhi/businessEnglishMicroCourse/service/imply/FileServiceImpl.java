package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.CoursewareEntity;
import org.shazhi.businessEnglishMicroCourse.repository.FileRepository;
import org.shazhi.businessEnglishMicroCourse.service.FileService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FileServiceImpl implements FileService {
    final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public List<CoursewareEntity> findCourseware(CoursewareEntity example){
        Example<CoursewareEntity> queryCondition = Example.of(example);
        return fileRepository.findAll(queryCondition);
    }

    @Override
    public CoursewareEntity findCoursewareById(Integer id) {
        return fileRepository.findById(id).get();
    }
}
