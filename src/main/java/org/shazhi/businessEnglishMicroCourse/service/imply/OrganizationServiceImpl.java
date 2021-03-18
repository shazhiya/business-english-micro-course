package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.*;
import org.shazhi.businessEnglishMicroCourse.repository.OrganizationRepository;
import org.shazhi.businessEnglishMicroCourse.service.OrganizationService;
import org.shazhi.businessEnglishMicroCourse.util.IList;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
    final OrganizationRepository repository;
    final EntityManager entityManager;

    @Value("${default.role.organizaiton-creator-default-security}")
    Integer [] securityList;

    public OrganizationServiceImpl(OrganizationRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Override
    public Result updateOrganization(OrganizationEntity update) {
        OrganizationEntity persist = repository.getOne(update.getOrganizationId()).setStatus(update.getStatus());
        repository.saveAndFlush(persist);
        return new Result().setSuccess("操作成功");
    }

    @Override
    public Result insertOrganization(OrganizationEntity insert,UserEntity creator) {
        UserEntity user = entityManager.getReference(UserEntity.class,creator.getUserId());
        insert = repository.save(insert);

        List<SecurityEntity> securities = new ArrayList<>();
        for (Integer securityId : securityList) {
            securities.add(new SecurityEntity().setSecurityId(securityId));
        }
        entityManager.merge(new UserRoleOrganization()
                .setOrganization(insert)
                .setUser(user)
                .setRole(new RoleEntity()
                        .setSecurities(securities)
                        .setRoleName(insert.getOrganizationName()+"-创建者")));
        return new Result().setSuccess();
    }

    @Override
    public List<OrganizationEntity> load(OrganizationEntity example) {
        return repository.findAll(Example.of(example));
    }

    @Override
    public synchronized Result updateRole(UserRoleOrganization uro, String type) {
        if ("addRole".equals(type)){
            OrganizationEntity persist = repository.getOne(uro.getOrganization().getOrganizationId());
            uro.setOrganization(persist);
            entityManager.merge(uro);
        }

        if ("delRole".equals(type)){
            entityManager.remove(entityManager.find(RoleEntity.class,uro.getRole().getRoleId()));
        }

        if ("addSecurityToRole".equals(type)){
            RoleEntity role = entityManager.find(RoleEntity.class, uro.getRole().getRoleId());
            if (role.getSecurities().stream().noneMatch(securityEntity -> securityEntity.getSecurityId()==uro.getRole().getSecurities().get(0).getSecurityId())){
                role.getSecurities().add(entityManager.find(SecurityEntity.class,uro.getRole().getSecurities().get(0).getSecurityId()));
                entityManager.merge(role);
            }
        }

        return new Result().setSuccess();
    }

}
