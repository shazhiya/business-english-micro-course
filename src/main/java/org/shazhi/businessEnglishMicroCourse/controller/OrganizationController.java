package org.shazhi.businessEnglishMicroCourse.controller;

import org.shazhi.businessEnglishMicroCourse.entity.OrganizationEntity;
import org.shazhi.businessEnglishMicroCourse.entity.UserRoleOrganization;
import org.shazhi.businessEnglishMicroCourse.service.OrganizationService;
import org.shazhi.businessEnglishMicroCourse.util.IdUser;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;
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
        return organizationService.load(example);
    }

    @RequestMapping("audit")
    public Result audit(@RequestBody OrganizationEntity audit){
        return organizationService.updateOrganization(audit);
    }

    @RequestMapping("updateRole/{type}")
    public Result updateRole(@RequestBody UserRoleOrganization uro, @PathVariable String type){
        return organizationService.updateRole(uro,type);
    }

    private IdUser getUserDetail(){
        return (IdUser) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
