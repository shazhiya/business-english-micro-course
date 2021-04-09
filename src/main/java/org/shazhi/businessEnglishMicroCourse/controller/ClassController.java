package org.shazhi.businessEnglishMicroCourse.controller;

import org.shazhi.businessEnglishMicroCourse.entity.ClazzEntity;
import org.shazhi.businessEnglishMicroCourse.entity.ClazzUserEntity;
import org.shazhi.businessEnglishMicroCourse.service.ClassService;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("change")
    public Result change(@RequestBody ClazzEntity clazz){
        return classService.change(clazz);
    }

    @RequestMapping("saveCU")
    public Result saveCU(@RequestBody ClazzUserEntity cu){
        return classService.saveCU(cu);
    }

    @RequestMapping("changeCU")
    public Result changeCU(@RequestBody ClazzUserEntity cu){
        return classService.changeCU(cu);
    }

    @RequestMapping("search/{type}/{start}/{size}")
    public List<ClazzEntity> search(@PathVariable Integer size, @PathVariable Integer start, @PathVariable String type,@RequestBody ClazzEntity clazz){
        List<ClazzEntity> searches = classService.search(size, start, type, clazz);
        return searches;
    }
}
