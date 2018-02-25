package org.tim.notes.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class NoteDoc {
    private String body;
}
