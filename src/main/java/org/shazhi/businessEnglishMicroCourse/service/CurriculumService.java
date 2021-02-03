package org.shazhi.businessEnglishMicroCourse.service;


import org.shazhi.businessEnglishMicroCourse.entity.CurriculumEntity;

import java.util.List;

public interface CurriculumService {
    Boolean createCurriculum(CurriculumEntity curriculum);

    List<CurriculumEntity> getAll();

    List<CurriculumEntity> search(CurriculumEntity curriculumEntity, Integer start, Integer size);
}
