package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.OrganizationEntity;
import org.shazhi.businessEnglishMicroCourse.entity.RoleEntity;
import org.shazhi.businessEnglishMicroCourse.entity.UserEntity;
import org.shazhi.businessEnglishMicroCourse.entity.UserRoleOrganization;
import org.shazhi.businessEnglishMicroCourse.repository.OrganizationRepository;
import org.shazhi.businessEnglishMicroCourse.service.OrganizationService;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
    final OrganizationRepository repository;
    final EntityManager entityManager;


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
        entityManager.merge(new UserRoleOrganization()
                .setOrganization(insert)
                .setUser(user)
                .setRole(new RoleEntity()
                        .setRoleName(insert.getOrganizationName()+"-创建者")));

        return new Result().setSuccess();
    }

    @Override
    public List<OrganizationEntity> load(OrganizationEntity example) {
        return repository.findAll(Example.of(example));
    }
}
