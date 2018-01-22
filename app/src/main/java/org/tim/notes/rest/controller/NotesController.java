package org.tim.notes.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.tim.notes.domain.NotesDao;
import org.tim.notes.domain.model.NoteEntity;
import org.tim.notes.rest.dto.NoteDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/notes", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class NotesController {
    @Autowired
    private NotesDao notesDao;

    @GetMapping
    public List<NoteDto> getNotes() {
        return notesDao.getAll().stream().map(noteEntity -> noteEntity.toDto()).collect(Collectors.toList());
    }

    @GetMapping("/{noteId}")
    public NoteDto getNote(@PathVariable UUID noteId) {
        return notesDao.getById(noteId).toDto();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public NoteDto createNote(@RequestBody NoteDto dto) {
        NoteEntity entity = notesDao.createNote();
        entity.fromDto(dto);
        entity.save();
        return entity.toDto();
    }

    @PutMapping("/{noteId}")
    public NoteDto updateNote(@PathVariable UUID noteId, @RequestBody NoteDto note) {
        NoteEntity entity = notesDao.getById(noteId);
        entity.fromDto(note);
        entity.save();
        return entity.toDto();
    }

    @DeleteMapping("/{noteId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable UUID noteId) {
        NoteEntity entity = notesDao.getById(noteId);
        entity.delete();
    }
}

