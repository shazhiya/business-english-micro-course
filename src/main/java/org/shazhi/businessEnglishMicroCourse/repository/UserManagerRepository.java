package org.shazhi.businessEnglishMicroCourse.repository;


import org.shazhi.businessEnglishMicroCourse.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface UserManagerRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity>, Serializable {

}
