package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.UserEntity;
import org.shazhi.businessEnglishMicroCourse.repository.UserManagerRepository;
import org.shazhi.businessEnglishMicroCourse.service.UserManagerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserManagerServiceImpl implements UserManagerService {

    final UserManagerRepository userManager;

    public UserManagerServiceImpl(UserManagerRepository userManager) {
        this.userManager = userManager;
    }


    @Override
    public Map<String, Object> userList(Integer start, Integer width, UserEntity query) {

        Map<String, Object> result = new HashMap<>();

        Page<UserEntity> page = userManager.findAll((Specification<UserEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate p = criteriaBuilder.like(root.get("userName"), '%' + query.getUserName() + '%');
            if (query.getUserEnable() != null)
                p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("userEnable"), query.getUserEnable()));
            if (new ArrayList<>(query.getUros()).get(0).getRole().getRoleId() != null)
                p = criteriaBuilder.and(p, criteriaBuilder.equal(root.join("uros", JoinType.INNER).join("role").get("roleId"), new ArrayList<>(query.getUros()).get(0).getRole().getRoleId()));
            return criteriaQuery
                    .where(p)
                    .groupBy(root.get("userId"))
                    .getRestriction();

        }, PageRequest.of(start, width));

        result.put("totalPage", page.getTotalPages());
        result.put("totalUser", page.getTotalElements());
        result.put("userList", page.getContent().stream().map(UserEntity::ignoreAttr).collect(Collectors.toList()));


        return result;
    }

    @Override
    public Boolean update(UserEntity user) {
        try {
            userManager.save(userManager.findById(user.getUserId()).get()
                    .setUserHeadicon(user.getUserHeadicon()))
                    .setUserIntro(user.getUserIntro())
                    .setUserTelephone(user.getUserTelephone());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
