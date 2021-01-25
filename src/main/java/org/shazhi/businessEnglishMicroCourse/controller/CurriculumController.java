package org.shazhi.businessEnglishMicroCourse.controller;

import org.shazhi.businessEnglishMicroCourse.entity.CurriculumEntity;
import org.shazhi.businessEnglishMicroCourse.service.CurriculumService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("create")
    public Boolean create(@RequestBody CurriculumEntity curriculum) {
        return curriculumService.createCurriculum(curriculum);
    }

    @RequestMapping("all")
    public List<CurriculumEntity> all() {
        return curriculumService.getAll();
    }
}
