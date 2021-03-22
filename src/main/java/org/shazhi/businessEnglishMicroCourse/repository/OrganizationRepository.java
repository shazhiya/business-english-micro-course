package org.shazhi.businessEnglishMicroCourse.repository;

import org.shazhi.businessEnglishMicroCourse.entity.OrganizationEntity;
import org.shazhi.businessEnglishMicroCourse.entity.UserRoleOrganization;
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

    @Query("select uro from UserRoleOrganization uro where uro.user.userId = :uid and uro.organization.organizationId = :oid")
    UserRoleOrganization findUro(@Param("uid") Integer userId, @Param("oid") Integer organId);

    @Modifying
    @Query("delete from UserRoleOrganization uro where uro.user.userId = :uid and uro.organization.organizationId =:oid")
    Integer deleteMember(@Param("uid") Integer userId,@Param("oid") Integer organizationId);

    @Modifying
    @Query(nativeQuery = true,value = "delete from user_role_organization where role_id = :roleId")
    Integer delRole(@Param("roleId") Integer roleId);
}
