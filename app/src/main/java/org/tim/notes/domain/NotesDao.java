package org.tim.notes.domain;

import org.jooq.DSLContext;
import org.jooq.Record2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.notes.domain.dao.UserDao;
import org.tim.notes.domain.model.NoteEntity;

import java.util.List;
import java.util.UUID;

import static org.tim.notes.data.jooq.Tables.NOTES;

@Service
public class NotesDao {
    @Autowired
    private DSLContext jooq;
    @Autowired
    private UserDao userDao;

    public List<NoteEntity> getAll() {
        return jooq
                .select(NOTES.ID, NOTES.TITLE)
                .from(NOTES)
                .fetch((rec) -> mapNote(rec));
    }

    public NoteEntity getById(UUID id) {
        return jooq.select(NOTES.ID, NOTES.TITLE)
                .from(NOTES)
                .fetchOne(rec -> mapNote(rec));
    }

    public NoteEntity createNote() {
        return NoteEntity.builder()
                .user(userDao.getDummyUser())
                .jooq(jooq)
                .build();
    }

    private NoteEntity mapNote(Record2<UUID, String> rec) {
        return NoteEntity.builder()
                .id(rec.get(NOTES.ID))
                .title(rec.get(NOTES.TITLE))
                .description(rec.get(NOTES.DESCRIPTION))
                .jooq(jooq)
                .build();
    }
}
