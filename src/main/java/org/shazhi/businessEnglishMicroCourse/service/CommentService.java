package org.shazhi.businessEnglishMicroCourse.service;

import org.shazhi.businessEnglishMicroCourse.entity.CommentEntity;
import org.shazhi.businessEnglishMicroCourse.util.Result;

import java.util.List;

public interface CommentService {
    Result mark(CommentEntity comment);

    List<CommentEntity> load(CommentEntity comment);
}
