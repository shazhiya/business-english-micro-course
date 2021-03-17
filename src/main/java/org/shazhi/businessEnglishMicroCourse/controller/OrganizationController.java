package org.shazhi.businessEnglishMicroCourse.controller;

import org.shazhi.businessEnglishMicroCourse.entity.OrganizationEntity;
import org.shazhi.businessEnglishMicroCourse.entity.UserRoleOrganization;
import org.shazhi.businessEnglishMicroCourse.service.OrganizationService;
import org.shazhi.businessEnglishMicroCourse.util.IdUser;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("organization")
public class OrganizationController {

    final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping("applyOrganization")
    public Result applyOrgan(@RequestBody OrganizationEntity apply){
        return organizationService.insertOrganization(apply.setStatus("待审核"),getUserDetail().getUserInfo());
    }

    @RequestMapping("load")
    public List<OrganizationEntity> load(@RequestBody OrganizationEntity example){
        return organizationService.load(example).stream().map(OrganizationEntity::ignore).collect(Collectors.toList());
    }

    @RequestMapping("audit")
    public Result audit(@RequestBody OrganizationEntity audit){
        return organizationService.updateOrganization(audit);
    }

    private IdUser getUserDetail(){
        return (IdUser) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
