package org.tim.notes.domain.dao;

import org.jooq.DSLContext;
import org.jooq.Record2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.notes.domain.model.UserEntity;

import java.util.UUID;

import static org.tim.notes.data.jooq.tables.Users.USERS;

@Service
public class UserDao {
    @Autowired
    private DSLContext jooq;

    public UserEntity getById(UUID id) {
        return jooq.select(USERS.ID, USERS.USER_NAME).from(USERS).where(USERS.ID.eq(id))
                .fetchOne(rec -> mapUser(rec));
    }

    private UserEntity mapUser(Record2<UUID, String> rec) {
        return UserEntity.builder()
                .id(rec.get(USERS.ID))
                .userName(rec.get(USERS.USER_NAME))
                .jooq(jooq)
                .build();
    }

    public UserEntity getDummyUser() {
        return UserEntity.builder()
                .id(UUID.fromString("22ac2159-0ecc-4448-b01e-53eebf8f0ae7"))
                .build();
    }
}
