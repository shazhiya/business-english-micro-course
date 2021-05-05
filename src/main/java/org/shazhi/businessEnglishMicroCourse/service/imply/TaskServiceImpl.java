package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.QuestionEntity;
import org.shazhi.businessEnglishMicroCourse.entity.TaskEntity;
import org.shazhi.businessEnglishMicroCourse.repository.TaskRepository;
import org.shazhi.businessEnglishMicroCourse.service.TaskService;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Result assignTask(TaskEntity task) {
        for (QuestionEntity question : task.getQuestions()) {
            question.setTask(task);
        }
        taskRepository.save(task);
        return new Result().setSuccess();
    }

    @Override
    public List<TaskEntity> load(TaskEntity task) {
        return taskRepository.findAll(Example.of(task));
    }
}
