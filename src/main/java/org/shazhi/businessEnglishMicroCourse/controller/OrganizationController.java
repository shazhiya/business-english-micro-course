package org.shazhi.businessEnglishMicroCourse.controller;

import org.shazhi.businessEnglishMicroCourse.entity.OrganizationEntity;
import org.shazhi.businessEnglishMicroCourse.entity.UserEntity;
import org.shazhi.businessEnglishMicroCourse.service.OrganizationService;
import org.shazhi.businessEnglishMicroCourse.util.IdUser;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("organization")
public class OrganizationController {

    final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping("applyOrganization")
    public Result applyOrgan(@RequestBody OrganizationEntity apply){
        return organizationService
                .updateOrganization(apply.setStatus("待审核")
                        .setCreator(new UserEntity()
                                .setUserId(getUserDetail()
                                        .getUserId())));
    }

    private IdUser getUserDetail(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().toString());
        return (IdUser) (SecurityContextHolder.getContext().getAuthentication());
    }
}
