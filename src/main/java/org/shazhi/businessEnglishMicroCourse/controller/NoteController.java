package org.shazhi.businessEnglishMicroCourse.controller;

import org.shazhi.businessEnglishMicroCourse.entity.NoteEntity;
import org.shazhi.businessEnglishMicroCourse.entity.NotepadEntity;
import org.shazhi.businessEnglishMicroCourse.service.NoteService;
import org.shazhi.businessEnglishMicroCourse.util.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("note")
public class NoteController {
    final private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping("createNotepad")
    public Result createNotepad(@RequestBody NotepadEntity notepad){
        return noteService.createNotepad(notepad);
    }

    @RequestMapping("deleteNotepad")
    public Result deleteNotepad(@RequestBody NotepadEntity notepad){
        return noteService.deleteNotepad(notepad);
    }

    @RequestMapping("loadNotepads")
    public List<NotepadEntity> loadNotepads(@RequestBody NotepadEntity notepad){
        return noteService.loadNotepads(notepad);
    }

    @RequestMapping("loadNotes")
    public List<NoteEntity> loadNote(@RequestBody NoteEntity note){
        return noteService.loadNote(note);
    }

    @RequestMapping("write")
    public Result write(@RequestBody NoteEntity note){
        return noteService.writeNote(note);
    }

    @RequestMapping("garbage")
    public Result garbage(@RequestBody NoteEntity note){
        return noteService.garbage(note);
    }

}
