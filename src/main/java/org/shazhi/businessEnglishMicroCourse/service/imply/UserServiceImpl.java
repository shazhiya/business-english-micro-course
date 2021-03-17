package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.UserEntity;
import org.shazhi.businessEnglishMicroCourse.repository.UserRepository;
import org.shazhi.businessEnglishMicroCourse.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    @Value("${default.headIco}")
    String headIco;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Integer register(UserEntity registerUser) {
        try {
            registerUser
                    .setUserHeadicon(headIco)
                    .setUserEnable(true)
                    .setUserIntro("this is very lazy");
            registerUser = userRepository.save(registerUser);
            if (registerUser.getUserId() == null) return -1;
            else return 1;
        } catch (Exception exception) {
            return -5;
        }
    }

    @Override
    public Map<String, Object> querySecuritiesByUsername(UserEntity user) {
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }

    @Override
    public UserEntity getProfileByUsername(UserEntity user) {
        user = userRepository.getProfileByUsername(user.getUserName());
        if (user==null) user = new UserEntity();
        return user.ignoreAttr();
    }

    @Override
    public Boolean validateEmailAvailable(String email) {
        return userRepository.findUserEntitiesByUserEmail(email).size() == 0;
    }

    @Override
    public Boolean validateUserNameAvailable(String username) {
        return userRepository.findUserEntitiesByUserName(username).size() == 0;
    }

    @Override
    public UserEntity findUserByUserEmail(String email) {
        return userRepository.findOne(Example.of(new UserEntity().setUserEmail(email))).get();
    }

    @Override
    public void update(UserEntity userEntity) {
        userRepository.saveAndFlush(userEntity);
    }

}
