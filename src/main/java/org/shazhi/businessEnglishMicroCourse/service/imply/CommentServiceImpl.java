package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.CommentEntity;
import org.shazhi.businessEnglishMicroCourse.repository.CommentRepository;
import org.shazhi.businessEnglishMicroCourse.service.CommentService;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Result mark(CommentEntity comment) {
        commentRepository.save(comment);
        return new Result().setSuccess();
    }

    @Override
    public List<CommentEntity> load(CommentEntity comment) {
        return commentRepository.findAll(Example.of(comment));
    }
}
