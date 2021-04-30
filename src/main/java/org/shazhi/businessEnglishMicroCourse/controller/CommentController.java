package org.shazhi.businessEnglishMicroCourse.controller;

import org.shazhi.businessEnglishMicroCourse.entity.CommentEntity;
import org.shazhi.businessEnglishMicroCourse.service.CommentService;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {
    final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("mark")
    public Result mark(@RequestBody CommentEntity comment){
        return commentService.mark(comment);
    }

    @RequestMapping("load/{start}")
    public List<CommentEntity> load(@RequestBody CommentEntity comment, @PathVariable Integer start){
        return commentService.load(comment,start);
    }

}
