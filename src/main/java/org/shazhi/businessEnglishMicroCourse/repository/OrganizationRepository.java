package org.shazhi.businessEnglishMicroCourse.repository;

import org.shazhi.businessEnglishMicroCourse.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity,Integer> , JpaSpecificationExecutor<OrganizationEntity> {
}
