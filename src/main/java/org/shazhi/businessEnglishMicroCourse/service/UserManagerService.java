package org.shazhi.businessEnglishMicroCourse.service;

import org.shazhi.businessEnglishMicroCourse.entity.UserEntity;

import java.util.Map;

public interface UserManagerService {
    Map<String, Object> userList(Integer start, Integer width, UserEntity query);

    Boolean update(UserEntity user);
}
