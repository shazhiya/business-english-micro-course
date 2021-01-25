package org.shazhi.businessEnglishMicroCourse.service;


import org.shazhi.businessEnglishMicroCourse.entity.RoleEntity;
import org.shazhi.businessEnglishMicroCourse.entity.UserEntity;

import java.util.List;

public interface SecurityService {
    List<RoleEntity> getAllRole();

    Boolean setUserEnable(UserEntity user);
}
