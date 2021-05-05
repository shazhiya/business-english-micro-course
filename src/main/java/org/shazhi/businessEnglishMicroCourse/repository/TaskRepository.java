package org.shazhi.businessEnglishMicroCourse.repository;

import org.shazhi.businessEnglishMicroCourse.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Integer>, JpaSpecificationExecutor<TaskEntity> {

}
