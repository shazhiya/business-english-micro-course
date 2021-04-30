package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.CommentEntity;
import org.shazhi.businessEnglishMicroCourse.repository.CommentRepository;
import org.shazhi.businessEnglishMicroCourse.service.CommentService;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Result mark(CommentEntity comment) {
        comment = commentRepository.save(comment);
        return new Result().setSuccess().setData(comment.getCommentId());
    }

    @Override
    public List<CommentEntity> load(CommentEntity comment, Integer start) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"createdTime"));
        return commentRepository
                .queryFirstComment(PageRequest.of(start/10,10,Sort.by(orders)),comment.getChapter().getChapterId(),comment.getType());
    }
}
