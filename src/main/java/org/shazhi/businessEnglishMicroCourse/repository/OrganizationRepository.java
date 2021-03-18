package org.shazhi.businessEnglishMicroCourse.repository;

import org.shazhi.businessEnglishMicroCourse.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity,Integer> , JpaSpecificationExecutor<OrganizationEntity> {

    @Modifying
    @Query(nativeQuery = true, value = "delete from role_security rs where rs.role_id= :roleId and rs.security_id = :securityId")
    Integer delRoleSecurity(@Param("roleId") Integer roleId,@Param("securityId") Integer securityId);
}
