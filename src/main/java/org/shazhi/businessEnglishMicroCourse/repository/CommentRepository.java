package org.shazhi.businessEnglishMicroCourse.repository;

import org.shazhi.businessEnglishMicroCourse.entity.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Integer>, JpaSpecificationExecutor<CommentEntity> {
    @Query("from CommentEntity comm where comm.parent is null and comm.chapter.chapterId =:chapterId and type =:type")
    List<CommentEntity> queryFirstComment(Pageable page, @Param("chapterId") Integer chapterId,@Param("type") String type);
}
