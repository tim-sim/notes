package org.tim.notes.domain.model;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Getter;
import org.jooq.DSLContext;
import org.tim.notes.data.jooq.tables.Notes;
import org.tim.notes.data.jooq.tables.records.NotesRecord;
import org.tim.notes.rest.dto.NoteDto;

import java.util.Set;
import java.util.UUID;

import static org.tim.notes.data.jooq.tables.Notes.NOTES;

@Builder
@Getter
public class NoteEntity implements BaseDomainEntity {
    private UUID id;
    private String title;
    private String description;
    private Set<String> tags;
    private UserEntity user;

    private DSLContext jooq;

    @Override
    public NoteEntity save() {
        if (id == null) {
            this.id = jooq.insertInto(NOTES)
                    .columns(NOTES.TITLE, NOTES.DESCRIPTION, NOTES.USER_ID, NOTES.DOC)
                    .values(title, description, user.getId(), buildDoc())
                    .returning(NOTES.ID)
                    .fetchOne().getId();
        } else {
            jooq.update(NOTES)
                    .set(NOTES.TITLE, title)
                    .set(NOTES.DESCRIPTION, description)
                    .where(NOTES.ID.eq(id));
        }
        return this;
    }

    private Object buildDoc() {
        NoteDoc doc = NoteDoc.builder().tags(tags).build();
        return new Gson().toJson(doc);
    }

    @Override
    public void delete() {
        jooq.delete(NOTES).where(NOTES.ID.eq(id));
        id = null;
    }

    public NoteDto toDto() {
        return NoteDto.builder()
                .id(id)
                .title(title)
                .description(description)
                .build();
    }

    public NoteEntity fromDto(NoteDto dto) {
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.tags = dto.getTags();
        return this;
    }
}
