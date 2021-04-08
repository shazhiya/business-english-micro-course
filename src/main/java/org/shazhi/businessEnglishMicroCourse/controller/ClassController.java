package org.shazhi.businessEnglishMicroCourse.controller;

import org.shazhi.businessEnglishMicroCourse.entity.ClazzEntity;
import org.shazhi.businessEnglishMicroCourse.service.ClassService;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("class")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @RequestMapping("save")
    public Result saveClass(@RequestBody ClazzEntity clazz){
        return classService.save(clazz);
    }

    @RequestMapping("load")
    public List<ClazzEntity> load(@RequestBody ClazzEntity clazz){
        return classService.load(clazz);
    }


}
