package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.*;
import org.shazhi.businessEnglishMicroCourse.repository.OrganizationRepository;
import org.shazhi.businessEnglishMicroCourse.service.OrganizationService;
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
            RoleEntity role = entityManager.find(RoleEntity.class,uro.getRole().getRoleId());
            role.getUros().forEach(rUro->{
                if (rUro.getUser()!=null){
                    entityManager.merge(rUro.setRole(null));
                }
            });
            repository.delRole(role.getRoleId());
        }

        if ("addSecurityToRole".equals(type)){
            RoleEntity role = entityManager.find(RoleEntity.class, uro.getRole().getRoleId());
            if (role.getSecurities().stream().noneMatch(securityEntity -> securityEntity.getSecurityId().equals(uro.getRole().getSecurities().get(0).getSecurityId()))){
                role.getSecurities().add(entityManager.find(SecurityEntity.class,uro.getRole().getSecurities().get(0).getSecurityId()));
                entityManager.merge(role);
            }
        }

        if ("delSecurityToRole".equals(type)){
            repository.delRoleSecurity(uro.getRole().getRoleId(),uro.getRole().getSecurities().get(0).getSecurityId());
        }


        return new Result().setSuccess();
    }

    @Override
    public Result inviteMember(UserRoleOrganization uro) {
        OrganizationEntity organ = repository.getOne(uro.getOrganization().getOrganizationId());

        UserRoleOrganization beInviter = new UserRoleOrganization()
                .setOrganization(organ)
                .setStatus("待接受邀请")
                .setUser(entityManager.getReference(UserEntity.class,uro.getUser().getUserId()));
        MessageEntity organization_invite = new MessageEntity()
                .setMessageContent(organ.getCreator().getUserName() + " 邀请您加入 " + organ.getOrganizationName())
                .setOptions(organ.getOrganizationId() + "")
                .setSendUser(organ.getCreator())
                .setTargetUser(uro.getUser())
                .setStatus("未读")
                .setType("organization invite");
        entityManager.merge(beInviter);
        entityManager.merge(organization_invite);
        return new Result().setSuccess();
    }

    @Override
    public Result react(MessageEntity mess) {
        UserRoleOrganization uro = repository.findUro(mess.getTargetUser().getUserId(),Integer.valueOf(mess.getOptions()));
        uro.setStatus(mess.getStatus());
        entityManager.merge(mess);
        MessageEntity message = entityManager.find(MessageEntity.class,mess.getMessageId());
        message.setStatus(mess.getStatus());
        return new Result().setSuccess();
    }

    @Override
    public Result delMember(UserRoleOrganization uro) {
        repository.deleteMember(uro.getUser().getUserId(),uro.getOrganization().getOrganizationId());
        return new Result().setSuccess();
    }

    @Override
    public Result assignRole(UserRoleOrganization uro) {
        UserRoleOrganization persist = repository.findUro(uro.getUser().getUserId(),uro.getOrganization().getOrganizationId());
        RoleEntity roleEntity = entityManager.find(RoleEntity.class, uro.getRole().getRoleId());
        persist.setRole(roleEntity);
        entityManager.merge(persist);
        return new Result().setSuccess();
    }

}
