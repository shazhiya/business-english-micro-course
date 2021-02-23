package org.shazhi.businessEnglishMicroCourse.controller;

import org.shazhi.businessEnglishMicroCourse.addition.Email;
import org.shazhi.businessEnglishMicroCourse.entity.UserEntity;
import org.shazhi.businessEnglishMicroCourse.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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

    /*
        0: params is not complete
        1:  success
        -1: verify code is error
        -2: email is not available
        -3 user is not available
        -4 system error
     */
    @RequestMapping("register")
    public Integer register(@RequestBody UserEntity registerUser,HttpSession session) {

        if (registerUser.getUserName()==null|| registerUser.getPassword()==null|| registerUser.getUserEmail()==null){
            return 0;
        }

        String verifyCode = (String) session.getAttribute("verifyCode");
        if (verifyCode==null || !verifyCode.equals(registerUser.getUserEmail())){
            return -1;
        }
        String email = (String) session.getAttribute("currentEmail");
        if (!userService.validateEmailAvailable(email)) {
            return -2;
        }
        if (!userService.validateUserNameAvailable(registerUser.getUserName())){
            return -3;
        }

        try {
            registerUser.setUserEmail(email);
            return userService.register(registerUser);
        }catch (Exception e){
            e.printStackTrace();
            return -4;
        }
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

    @RequestMapping("auth/giveVerifyCode/{type}")
    public Boolean giveVerifyCode(@Param("email") String email, HttpSession session, @PathVariable String type) {
        Timer timer = new Timer();
        session.setAttribute("currentEmail",email);
        String vc = generateVerifyCode();
        session.setAttribute("verifyCode",vc);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (session!=null&&session.getAttribute("verifyCode")!=null){
                    session.removeAttribute("verifyCode");
                }
            }
        },60*1000*5);
        String content, title;
        if (type.equals("register")){
            title = "注册验证码";
        }else{
            title = "重置密码";
        }
        content = "您的验证码为： " + vc + "\n 若操作非本人所为，请忽略该信息。";
        return emailService.sendEmail(email,content,title);
    }

    /*
        0: 服务器异常
        -1: 参数缺失
        -2: 验证码错误
        -3: 邮箱未绑定
    */
    @RequestMapping("auth/resetPassword")
    public Integer resetPassword(@RequestBody UserEntity userEntity, HttpSession session){
        if (userEntity.getPassword()==null|| userEntity.getPassword().length()<6||userEntity.getUserEmail()==null){
            return -1;
        }

        String email = (String) session.getAttribute("currentEmail");
        String verifyCode = (String) session.getAttribute("verifyCode");
        if (!userEntity.getUserEmail().equals(verifyCode)){
            return -2;
        }
        if (userService.validateEmailAvailable(email)){
            return -3;
        }

        try{
            UserEntity resetUser = userService.findUserByUserEmail(email);
            if (resetUser==null) return 0;
            resetUser.setPassword(userEntity.getPassword());
            userService.update(userEntity);
            return 1;
        }catch (Exception e){
            return 0;
        }


    }

    private String generateVerifyCode(){
        String verifyCode = "";
        while (verifyCode.length()<6){
            verifyCode += (int)(Math.random()*10);
        }
        return verifyCode;
    }

}
