package org.shazhi.businessEnglishMicroCourse.service;

import org.shazhi.businessEnglishMicroCourse.entity.NoteEntity;
import org.shazhi.businessEnglishMicroCourse.entity.NotepadEntity;
import org.shazhi.businessEnglishMicroCourse.util.Result;

import java.util.List;

public interface NoteService {
    Result createNotepad(NotepadEntity notepad);

    Result deleteNotepad(NotepadEntity notepad);

    List<NotepadEntity> loadNotepads(NotepadEntity notepad);

    List<NoteEntity> loadNote(NoteEntity note);

    Result writeNote(NoteEntity note);

    Result garbage(NoteEntity note);
}
