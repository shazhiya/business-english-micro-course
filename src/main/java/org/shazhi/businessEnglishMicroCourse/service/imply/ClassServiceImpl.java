package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.ClazzEntity;
import org.shazhi.businessEnglishMicroCourse.repository.ClassRepository;
import org.shazhi.businessEnglishMicroCourse.service.ClassService;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;

    public ClassServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public Result save(ClazzEntity clazz) {
        classRepository.saveAndFlush(clazz);
        return new Result().setSuccess();
    }

    @Override
    public List<ClazzEntity> load(ClazzEntity clazz) {
        return classRepository.findAll(Example.of(clazz));
    }
}
