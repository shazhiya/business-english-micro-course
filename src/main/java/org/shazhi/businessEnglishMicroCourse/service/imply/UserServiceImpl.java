package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.RoleEntity;
import org.shazhi.businessEnglishMicroCourse.entity.SecurityEntity;
import org.shazhi.businessEnglishMicroCourse.entity.UserEntity;
import org.shazhi.businessEnglishMicroCourse.repository.UserRepository;
import org.shazhi.businessEnglishMicroCourse.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
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
                    .setUserHeadico(headIco)
                    .setUserEnable(true)
                    .setUserIntro("this is very lazy");
            registerUser.setRoles(new ArrayList<>());
            registerUser.getRoles().add(new RoleEntity().setRoleId(2));
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
        UserEntity query = userRepository.getUserEntityByUserName(user.getUserName());
        result.put("roles", query.getRoles().stream().map(RoleEntity::getRoleName).collect(Collectors.toList()));
        result.put("securities", query.getRoles().stream().reduce(new HashSet<String>(), (set, role) -> {
            set.addAll(role.getSecurities().stream().map(SecurityEntity::getSecurityName).collect(Collectors.toList()));
            return set;
        }, (set, role) -> null));
        return result;
    }

    @Override
    public UserEntity getProfileByUsername(UserEntity user) {
        user = userRepository.getProfileByUsername(user.getUserName());
        if (user==null) user = new UserEntity();
        return UserEntity.ignoreAttr(user);
    }

    @Override
    public Boolean validateEmailAvailable(String email) {
        return userRepository.findUserEntitiesByUserEmail(email).size() == 0;
    }

    @Override
    public Boolean validateUserNameAvailable(String username) {
        return userRepository.findUserEntitiesByUserName(username).size() == 0;
    }

}
