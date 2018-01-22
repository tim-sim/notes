/*
 * This file is generated by jOOQ.
*/
package org.tim.notes.data.jooq;


import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;
import org.tim.notes.data.jooq.tables.FlywaySchemaHistory;
import org.tim.notes.data.jooq.tables.Notes;
import org.tim.notes.data.jooq.tables.Users;
import org.tim.notes.data.jooq.tables.records.FlywaySchemaHistoryRecord;
import org.tim.notes.data.jooq.tables.records.NotesRecord;
import org.tim.notes.data.jooq.tables.records.UsersRecord;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = UniqueKeys0.FLYWAY_SCHEMA_HISTORY_PK;
    public static final UniqueKey<NotesRecord> NOTES_PKEY = UniqueKeys0.NOTES_PKEY;
    public static final UniqueKey<UsersRecord> USERS_PKEY = UniqueKeys0.USERS_PKEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<NotesRecord, UsersRecord> NOTES__NOTES_USER_ID_FKEY = ForeignKeys0.NOTES__NOTES_USER_ID_FKEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, "flyway_schema_history_pk", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK);
        public static final UniqueKey<NotesRecord> NOTES_PKEY = createUniqueKey(Notes.NOTES, "notes_pkey", Notes.NOTES.ID);
        public static final UniqueKey<UsersRecord> USERS_PKEY = createUniqueKey(Users.USERS, "users_pkey", Users.USERS.ID);
    }

    private static class ForeignKeys0 extends AbstractKeys {
        public static final ForeignKey<NotesRecord, UsersRecord> NOTES__NOTES_USER_ID_FKEY = createForeignKey(org.tim.notes.data.jooq.Keys.USERS_PKEY, Notes.NOTES, "notes__notes_user_id_fkey", Notes.NOTES.USER_ID);
    }
}