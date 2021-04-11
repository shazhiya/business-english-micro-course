package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.ClazzEntity;
import org.shazhi.businessEnglishMicroCourse.entity.ClazzUserEntity;
import org.shazhi.businessEnglishMicroCourse.repository.ClassRepository;
import org.shazhi.businessEnglishMicroCourse.service.ClassService;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final EntityManager em;

    public ClassServiceImpl(ClassRepository classRepository, EntityManager em) {
        this.classRepository = classRepository;
        this.em = em;
    }

    @Override
    public Result save(ClazzEntity clazz) {
        clazz.getCcs().forEach(cc-> cc.setClazz(clazz));
        classRepository.saveAndFlush(clazz);
        return new Result().setSuccess();
    }

    @Override
    public List<ClazzEntity> load(ClazzEntity clazz) {
        return classRepository.findAll(Example.of(clazz));
    }

    @Override
    public Result change(ClazzEntity clazz) {
        clazz = classRepository.getOne(clazz.getClazzId()).setStatus(clazz.getStatus());
        classRepository.saveAndFlush(clazz);
        return new Result().setSuccess();
    }

    @Override
    public Result saveCU(ClazzUserEntity cu) {
        em.merge(cu);
        return new Result().setSuccess();
    }

    @Override
    public Result changeCU(ClazzUserEntity cu) {
        cu = em.find(cu.getClass(),cu.getClazzUserId()).setStatus(cu.getStatus());
        em.persist(cu);
        return new Result().setSuccess();
    }

    @Override
    public List<ClazzEntity> search(Integer size, Integer start, String type, ClazzEntity clazz) {
        return classRepository.findAll(Example.of(clazz), PageRequest.of(start,size)).getContent();
    }
}
