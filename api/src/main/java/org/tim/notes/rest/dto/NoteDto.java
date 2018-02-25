package org.tim.notes.rest.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class NoteDto {
    private UUID id;
    private String name;
    private String body;
    private List<String> tags;
}
