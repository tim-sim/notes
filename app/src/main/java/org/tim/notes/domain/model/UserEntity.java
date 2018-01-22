package org.tim.notes.domain.model;

import lombok.Builder;
import lombok.Getter;
import org.jooq.DSLContext;

import java.util.UUID;

@Getter
@Builder
public class UserEntity {
    private DSLContext jooq;

    private UUID id;
    private String userName;
}
