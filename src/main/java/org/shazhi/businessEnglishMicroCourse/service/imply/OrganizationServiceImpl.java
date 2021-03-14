package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.OrganizationEntity;
import org.shazhi.businessEnglishMicroCourse.repository.OrganizationRepository;
import org.shazhi.businessEnglishMicroCourse.service.OrganizationService;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
    final OrganizationRepository repository;

    public OrganizationServiceImpl(OrganizationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result updateOrganization(OrganizationEntity update){
        try {
            repository.saveAndFlush(update);
            return new Result().setSuccess();
        }catch (Exception e){
            return new Result().setMsg("内部错误"+e.toString());
        }
    }
}
