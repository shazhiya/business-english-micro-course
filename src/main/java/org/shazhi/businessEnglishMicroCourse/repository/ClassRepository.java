package org.shazhi.businessEnglishMicroCourse.repository;

import org.shazhi.businessEnglishMicroCourse.entity.ClazzEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClazzEntity,Integer>, JpaSpecificationExecutor<ClazzEntity> {
    @Query("select distinct cu.clazz from ClazzUserEntity cu where cu.user.userId =:userId")
    List<ClazzEntity> loadMyClasses(@Param("userId") Integer userId);
}
