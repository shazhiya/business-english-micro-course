package org.shazhi.businessEnglishMicroCourse.service;


import org.shazhi.businessEnglishMicroCourse.entity.CurriculumEntity;

import java.util.List;

public interface CurriculumService {
    Boolean createCurriculum(CurriculumEntity curriculum);

    List<CurriculumEntity> getAll();

}
