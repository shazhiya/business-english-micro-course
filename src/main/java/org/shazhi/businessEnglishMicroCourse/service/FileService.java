package org.shazhi.businessEnglishMicroCourse.service;

import org.shazhi.businessEnglishMicroCourse.entity.CoursewareEntity;

import java.util.List;

public interface FileService {
    List<CoursewareEntity> findCourseware(CoursewareEntity example);
}
