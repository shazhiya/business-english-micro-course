package org.shazhi.businessEnglishMicroCourse.service;


import org.shazhi.businessEnglishMicroCourse.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserEntity> findAll();

    Integer register(UserEntity registerUser);

    Map<String, Object> querySecuritiesByUsername(UserEntity user);

    UserEntity getProfileByUsername(UserEntity user);

    Boolean validateEmailAvailable(String email);

    Boolean validateUserNameAvailable(String username);
}
