package org.shazhi.businessEnglishMicroCourse.service;

import org.shazhi.businessEnglishMicroCourse.entity.TaskEntity;
import org.shazhi.businessEnglishMicroCourse.util.Result;

import java.util.List;

public interface TaskService {

    Result assignTask(TaskEntity task);

    List<TaskEntity> load(TaskEntity task);
}
