package org.tim.notes.domain.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.Builder;
import lombok.Getter;
import org.jooq.DSLContext;
import org.tim.notes.rest.dto.NoteDto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.tim.notes.data.jooq.tables.Notes.NOTES;
import static org.tim.notes.data.jooq.tables.Tags.TAGS;

@Builder
@Getter
public class NoteEntity implements BaseDomainEntity {
    private UUID id;
    private String name;
    private String body;
    private List<UUID> tags;
    private UserEntity user;

    private DSLContext jooq;

    @Override
    public NoteEntity save() {
        if (id == null) {
            this.id = jooq.insertInto(NOTES)
                    .columns(NOTES.NAME, NOTES.USER_ID, NOTES.DOC, NOTES.TAGS)
                    .values(name, user.getId(), buildDoc(), tags.toArray(new UUID[]{}))
                    .returning(NOTES.ID)
                    .fetchOne()
                    .get(NOTES.ID);
        } else {
            jooq.update(NOTES)
                    .set(NOTES.NAME, name)
                    .set(NOTES.DOC, buildDoc())
                    .set(NOTES.TAGS, tags.toArray(new UUID[]{}))
                    .where(NOTES.ID.eq(id))
                    .execute();
        }
        return this;
    }

    private JsonElement buildDoc() {
        NoteDoc doc = NoteDoc.builder().body(body).build();
        return new Gson().toJsonTree(doc);
    }

    @Override
    public void delete() {
        jooq.delete(NOTES).where(NOTES.ID.eq(id));
        id = null;
    }

    public NoteDto toDto() {
        return NoteDto.builder()
                .id(id)
                .name(name)
                .body(body)
                .tags(fetchTags())
                .build();
    }

    public NoteEntity fromDto(NoteDto dto) {
        this.name = dto.getName();
        this.body = dto.getBody();
        this.tags = resolveTags(dto.getTags());
        return this;
    }

    private List<String> fetchTags() {
        return jooq.select(TAGS.NAME).from(TAGS).where(TAGS.ID.in(tags)).fetch(TAGS.NAME);
    }

    private List<UUID> resolveTags(List<String> tags) {
        return tags.stream()
                .filter(Objects::nonNull)
                .map(tag -> fetchTag(tag).orElseGet(() -> createTag(tag)))
                .collect(Collectors.toList());
    }

    private UUID createTag(String tag) {
        return jooq.insertInto(TAGS)
                .columns(TAGS.NAME)
                .values(tag.trim().toLowerCase())
                .returning(TAGS.ID)
                .fetchOne().get(TAGS.ID);
    }

    private Optional<UUID> fetchTag(String tag) {
        return jooq.select(TAGS.ID)
                .from(TAGS)
                .where(TAGS.NAME.equalIgnoreCase(tag.trim()))
                .fetchOptional(TAGS.ID);
    }
}
