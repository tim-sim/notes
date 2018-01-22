package org.tim.notes.domain.model;

import lombok.Builder;
import lombok.Getter;
import org.jooq.DSLContext;
import org.tim.notes.data.jooq.tables.Notes;
import org.tim.notes.rest.dto.NoteDto;

import java.util.UUID;

import static org.tim.notes.data.jooq.tables.Notes.NOTES;

@Builder
@Getter
public class NoteEntity implements BaseDomainEntity {
    private UUID id;
    private String title;
    private String description;
    private UserEntity user;

    private DSLContext jooq;

    @Override
    public void save() {
        if (id == null) {
            jooq.insertInto(NOTES)
                    .columns(NOTES.TITLE, NOTES.DESCRIPTION, NOTES.USER_ID)
                    .values(title, description, user.getId())
            .execute();
        } else {
            jooq.update(NOTES)
                    .set(NOTES.TITLE, title)
                    .set(NOTES.DESCRIPTION, description)
                    .where(NOTES.ID.eq(id));
        }
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
        return this;
    }
}
