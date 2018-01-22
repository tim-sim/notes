/*
 * This file is generated by jOOQ.
*/
package org.tim.notes.data.jooq.tables;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.tim.notes.data.jooq.Indexes;
import org.tim.notes.data.jooq.Keys;
import org.tim.notes.data.jooq.Public;
import org.tim.notes.data.jooq.tables.records.NotesRecord;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Notes extends TableImpl<NotesRecord> {

    private static final long serialVersionUID = -860459412;

    /**
     * The reference instance of <code>public.notes</code>
     */
    public static final Notes NOTES = new Notes();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<NotesRecord> getRecordType() {
        return NotesRecord.class;
    }

    /**
     * The column <code>public.notes.id</code>.
     */
    public final TableField<NotesRecord, UUID> ID = createField("id", org.jooq.impl.SQLDataType.UUID.nullable(false).defaultValue(org.jooq.impl.DSL.field("uuid_generate_v4()", org.jooq.impl.SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.notes.title</code>.
     */
    public final TableField<NotesRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.notes.description</code>.
     */
    public final TableField<NotesRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify how this type should be handled.
     */
    @java.lang.Deprecated
    public final TableField<NotesRecord, Object> DOC = createField("doc", org.jooq.impl.DefaultDataType.getDefaultDataType("jsonb"), this, "");

    /**
     * The column <code>public.notes.created_date</code>.
     */
    public final TableField<NotesRecord, Timestamp> CREATED_DATE = createField("created_date", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>public.notes.user_id</code>.
     */
    public final TableField<NotesRecord, UUID> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

    /**
     * Create a <code>public.notes</code> table reference
     */
    public Notes() {
        this(DSL.name("notes"), null);
    }

    /**
     * Create an aliased <code>public.notes</code> table reference
     */
    public Notes(String alias) {
        this(DSL.name(alias), NOTES);
    }

    /**
     * Create an aliased <code>public.notes</code> table reference
     */
    public Notes(Name alias) {
        this(alias, NOTES);
    }

    private Notes(Name alias, Table<NotesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Notes(Name alias, Table<NotesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.NOTES_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<NotesRecord> getPrimaryKey() {
        return Keys.NOTES_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<NotesRecord>> getKeys() {
        return Arrays.<UniqueKey<NotesRecord>>asList(Keys.NOTES_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<NotesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<NotesRecord, ?>>asList(Keys.NOTES__NOTES_USER_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Notes as(String alias) {
        return new Notes(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Notes as(Name alias) {
        return new Notes(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Notes rename(String name) {
        return new Notes(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Notes rename(Name name) {
        return new Notes(name, null);
    }
}