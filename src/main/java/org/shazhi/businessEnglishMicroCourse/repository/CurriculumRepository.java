package org.shazhi.businessEnglishMicroCourse.repository;

import org.shazhi.businessEnglishMicroCourse.entity.CurriculumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumRepository extends JpaRepository<CurriculumEntity, Integer>, JpaSpecificationExecutor<CurriculumEntity> {
}
