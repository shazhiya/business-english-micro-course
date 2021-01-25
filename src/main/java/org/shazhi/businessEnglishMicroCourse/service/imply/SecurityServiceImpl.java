package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.RoleEntity;
import org.shazhi.businessEnglishMicroCourse.entity.UserEntity;
import org.shazhi.businessEnglishMicroCourse.repository.SecurityRepository;
import org.shazhi.businessEnglishMicroCourse.service.SecurityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecurityServiceImpl implements SecurityService {
    final
    SecurityRepository securityRepository;

    public SecurityServiceImpl(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    @Override
    public List<RoleEntity> getAllRole() {
        return securityRepository
                .findAll()
                .stream()
                .map(RoleEntity::ignoreAttr)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean setUserEnable(UserEntity user) {
        return securityRepository.setEnable(user.getUserEnable(), user.getUserId()) == 1;
    }
}
