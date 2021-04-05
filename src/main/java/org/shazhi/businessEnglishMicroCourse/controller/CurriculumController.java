package org.shazhi.businessEnglishMicroCourse.controller;

import org.shazhi.businessEnglishMicroCourse.entity.CurriculumEntity;
import org.shazhi.businessEnglishMicroCourse.service.CurriculumService;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("curriculum")
@Transactional
public class CurriculumController {

    final
    CurriculumService curriculumService;

    public CurriculumController(CurriculumService curriculumService) {
        this.curriculumService = curriculumService;
    }

    @RequestMapping("create/{type}")
    public Boolean create(@RequestBody CurriculumEntity curriculum, @PathVariable String type) {
        if (type.equals("saveTemporarily")||type.equals("audited")){
            curriculum.setCurriculumStatus(type);
        }else {
            return false;
        }
        return curriculumService.createCurriculum(curriculum);
    }

    @RequestMapping("all")
    public List<CurriculumEntity> all() {
        return curriculumService.getAll();
    }

    @RequestMapping("load")
    public List<CurriculumEntity> load(@RequestBody CurriculumEntity course){
        return curriculumService.load(course);
    }

    @RequestMapping("change/{type}")
    public Result dispose(@RequestBody CurriculumEntity curriculum, @PathVariable String type){
        return curriculumService.changeStatus(curriculum,type);
    }

    @RequestMapping("search/{type}/{start}/{size}")
    public List<CurriculumEntity> search(HttpSession session ,@RequestBody CurriculumEntity curriculumEntity, @PathVariable String type, @PathVariable("start") Integer start, @PathVariable("size") Integer size) {
        return curriculumService.search(curriculumEntity, type,start, size);
    }
}
