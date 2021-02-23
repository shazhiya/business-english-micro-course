package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.CurriculumEntity;
import org.shazhi.businessEnglishMicroCourse.repository.CurriculumRepository;
import org.shazhi.businessEnglishMicroCourse.service.CurriculumService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CurriculumServiceImpl implements CurriculumService {
    final
    CurriculumRepository curriculumRepository;

    public CurriculumServiceImpl(CurriculumRepository curriculumRepository) {
        this.curriculumRepository = curriculumRepository;
    }

    @Override
    public Boolean createCurriculum(CurriculumEntity curriculum) {
        curriculum.getChapters().forEach(chapter -> {
            chapter.setCurriculum(curriculum).getCoursewares().forEach(ware -> {
                ware.setChapter(chapter);
            });
        });
        return curriculumRepository.saveAndFlush(curriculum).getCurriculumId() > -1;
    }

    @Override
    public List<CurriculumEntity> getAll() {
        return curriculumRepository.findAll();
    }

    @Override
    public List<CurriculumEntity> search(CurriculumEntity curriculumEntity, String type, Integer start, Integer size) {
        return curriculumRepository.findAll(PageRequest.of(start,size)).getContent();
    }
}
