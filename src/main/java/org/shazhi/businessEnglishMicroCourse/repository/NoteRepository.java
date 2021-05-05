package org.shazhi.businessEnglishMicroCourse.repository;

import org.shazhi.businessEnglishMicroCourse.entity.NoteEntity;
import org.shazhi.businessEnglishMicroCourse.entity.NotepadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository  extends JpaRepository<NoteEntity,Integer>, JpaSpecificationExecutor<Integer> {
    @Query("from NotepadEntity np where np.user.userId=:uid")
    List<NotepadEntity> queryNotepads(@Param("uid") Integer uid);
}
