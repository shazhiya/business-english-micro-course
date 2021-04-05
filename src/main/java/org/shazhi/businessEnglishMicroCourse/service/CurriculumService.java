package org.shazhi.businessEnglishMicroCourse.service;


import org.shazhi.businessEnglishMicroCourse.entity.CurriculumEntity;
import org.shazhi.businessEnglishMicroCourse.util.Result;

import java.util.List;

public interface CurriculumService {
    Boolean createCurriculum(CurriculumEntity curriculum);

    List<CurriculumEntity> getAll();

    List<CurriculumEntity> search(CurriculumEntity curriculumEntity, String type, Integer start, Integer size);

    List<CurriculumEntity> load(CurriculumEntity course);

    Result changeStatus(CurriculumEntity curriculum, String type);
}
