package org.shazhi.businessEnglishMicroCourse.controller;

import org.shazhi.businessEnglishMicroCourse.entity.RoleEntity;
import org.shazhi.businessEnglishMicroCourse.entity.UserEntity;
import org.shazhi.businessEnglishMicroCourse.service.SecurityService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("security")
@Transactional
public class SecurityController {

    final SecurityService securityService;

    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @RequestMapping("allRole")
    public List<RoleEntity> allRole() {
        return securityService
                .getAllRole();
    }

    @RequestMapping("setEnable")
    public Boolean setEnable(@RequestBody UserEntity user) {
        return securityService.setUserEnable(user);
    }
}
