package org.tim.notes.data.jooq.tables;

import com.google.gson.JsonElement;
import org.jooq.DataType;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.tim.notes.data.jooq.binding.PostgresJsonbBinding;

/**
 * @author tim
 * @since 2/25/2018
 */
public abstract class PostgresTableImpl extends TableImpl<Record> {
    protected final DataType<JsonElement> POSTGRES_JSONB_TYPE = SQLDataType.OTHER.asConvertedDataType(new PostgresJsonbBinding());

    public PostgresTableImpl(String name, String schema) {
        super(DSL.name(name), DSL.schema(DSL.name(schema)));
    }
}
