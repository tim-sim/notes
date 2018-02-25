package org.tim.notes.domain;

import com.google.gson.JsonElement;
import org.jooq.DSLContext;
import org.jooq.Record4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.notes.domain.dao.UserDao;
import org.tim.notes.domain.model.NoteEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.tim.notes.data.jooq.tables.Notes.NOTES;

@Service
public class NotesDao {
    @Autowired
    private DSLContext jooq;
    @Autowired
    private UserDao userDao;

    public List<NoteEntity> getAll() {
        return jooq
                .select(NOTES.ID, NOTES.NAME, NOTES.DOC, NOTES.TAGS)
                .from(NOTES)
                .fetch((rec) -> mapNote(rec));
    }

    public NoteEntity getById(UUID id) {
        return jooq.select(NOTES.ID, NOTES.NAME, NOTES.DOC, NOTES.TAGS)
                .from(NOTES)
                .where(NOTES.ID.eq(id))
                .fetchOne(rec -> mapNote(rec));
    }

    public NoteEntity createNote() {
        return NoteEntity.builder()
                .user(userDao.getDummyUser())
                .jooq(jooq)
                .build();
    }

    private NoteEntity mapNote(Record4<UUID, String, JsonElement, UUID[]> rec) {
        JsonElement doc = rec.get(NOTES.DOC);
        return NoteEntity.builder()
                .id(rec.get(NOTES.ID))
                .name(rec.get(NOTES.NAME))
                .body(doc.getAsJsonObject().get("body").getAsString())
                .tags(Arrays.asList(rec.get(NOTES.TAGS)))
                .jooq(jooq)
                .build();
    }
}
