package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.CoursewareEntity;
import org.shazhi.businessEnglishMicroCourse.repository.FileRepository;
import org.shazhi.businessEnglishMicroCourse.service.FileService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
}
