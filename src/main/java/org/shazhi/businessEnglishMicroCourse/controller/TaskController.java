package org.shazhi.businessEnglishMicroCourse.controller;

import org.shazhi.businessEnglishMicroCourse.entity.TaskEntity;
import org.shazhi.businessEnglishMicroCourse.service.TaskService;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping("assign")
    public Result assignTask(@RequestBody TaskEntity task){
        return taskService.assignTask(task);
    }

    @RequestMapping("load")
    public List<TaskEntity> load(@RequestBody TaskEntity task){
        return taskService.load(task);
    }
}
