package org.shazhi.businessEnglishMicroCourse.service;

import org.shazhi.businessEnglishMicroCourse.entity.ClazzEntity;
import org.shazhi.businessEnglishMicroCourse.entity.ClazzUserEntity;
import org.shazhi.businessEnglishMicroCourse.util.Result;

import java.util.List;

public interface ClassService {
    Result save(ClazzEntity clazz);

    List<ClazzEntity> load(ClazzEntity clazz);

    Result change(ClazzEntity clazz);

    Result saveCU(ClazzUserEntity cu);

    Result changeCU(ClazzUserEntity cu);
}
