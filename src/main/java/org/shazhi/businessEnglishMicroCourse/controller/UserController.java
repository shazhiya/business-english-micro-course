package org.shazhi.businessEnglishMicroCourse.controller;

import org.shazhi.businessEnglishMicroCourse.addition.Email;
import org.shazhi.businessEnglishMicroCourse.entity.UserEntity;
import org.shazhi.businessEnglishMicroCourse.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@RestController
public class UserController {
    final
    UserService userService;

    final Email emailService;

    public UserController(UserService userService, Email email) {
        this.userService = userService;
        this.emailService = email;
    }

    @RequestMapping("allUser")
    public List<UserEntity> getAllUser() {
        return userService.findAll();
    }

    @RequestMapping("register")
    public Integer register(@RequestBody UserEntity registerUser) {
        return userService.register(registerUser);
    }

    @RequestMapping("user/querySecuritiesByUsername")
    public Map<String, Object> querySecuritiesByUsername(@RequestBody UserEntity user) {
        return userService.querySecuritiesByUsername(user);
    }

    @RequestMapping("user/getProfileByUsername")
    public UserEntity getProfileByUsername(@RequestBody UserEntity user) {
        return userService.getProfileByUsername(user);
    }

    @RequestMapping("user/queryHeadIconByUsername")
    public UserEntity queryHeardIcoByUsername(@RequestBody UserEntity user) {
        return new UserEntity().setUserHeadico(userService.getProfileByUsername(user).getUserHeadico());
    }

    @RequestMapping("auth/giveVerifyCode")
    public Boolean giveVerifyCode(@Param("email") String email, Session session) {
        Timer timer = new Timer();
        session.setAttribute("currentEmail",email);
        String vc = generateVerifyCode();
        session.setAttribute("verifyCode",vc);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (session!=null&&!session.isExpired()&&session.getAttribute("verifyCode")!=null&&session.getAttribute("verifyCode")!=""){
                    session.removeAttribute("verifyCode");
                }
            }
        },60*1000);
        return emailService.sendEmail(email,"您的验证码为： " + vc + "\n 若操作非本人所为，请忽略该信息。","注册验证码");
    }

    private String generateVerifyCode(){
        String verifyCode = "";
        while (verifyCode.length()<6){
            verifyCode += (int)(Math.random()*10);
        }
        return verifyCode;
    }

}
