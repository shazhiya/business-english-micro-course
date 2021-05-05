package org.shazhi.businessEnglishMicroCourse.service.imply;

import org.shazhi.businessEnglishMicroCourse.entity.NoteEntity;
import org.shazhi.businessEnglishMicroCourse.entity.NotepadEntity;
import org.shazhi.businessEnglishMicroCourse.repository.NoteRepository;
import org.shazhi.businessEnglishMicroCourse.service.NoteService;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final EntityManager entityManager;
    public NoteServiceImpl(NoteRepository noteRepository, EntityManager entityManager) {
        this.noteRepository = noteRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Result createNotepad(NotepadEntity notepad) {
        entityManager.persist(notepad);
        return new Result().setSuccess();
    }

    @Override
    public Result deleteNotepad(NotepadEntity notepad) {
        notepad = entityManager.find(notepad.getClass(), notepad.getNotepadId());
        notepad.setStatus("delete");
        entityManager.merge(notepad);
        return new Result().setSuccess();
    }

    @Override
    public List<NotepadEntity> loadNotepads(NotepadEntity notepad) {
        return noteRepository.queryNotepads(notepad.getUser().getUserId());
    }

    @Override
    public List<NoteEntity> loadNote(NoteEntity note) {
        return noteRepository.findAll(Example.of(note));
    }

    @Override
    public Result writeNote(NoteEntity note) {
        noteRepository.save(note);
        return new Result().setSuccess();
    }

    @Override
    public Result garbage(NoteEntity note) {
        noteRepository.save(note);
        return new Result().setSuccess();
    }
}
