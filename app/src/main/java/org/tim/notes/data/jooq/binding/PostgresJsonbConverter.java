package org.tim.notes.data.jooq.binding;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import org.jooq.Converter;

/**
 * @author tim
 * @since 2/25/2018
 */
public class PostgresJsonbConverter implements Converter<Object, JsonElement> {
    @Override
    public JsonElement from(Object t) {
        return t == null ? JsonNull.INSTANCE : new Gson().fromJson("" + t, JsonElement.class);
    }

    @Override
    public Object to(JsonElement u) {
        return u == null || u == JsonNull.INSTANCE ? null : new Gson().toJson(u);
    }

    @Override
    public Class<Object> fromType() {
        return Object.class;
    }

    @Override
    public Class<JsonElement> toType() {
        return JsonElement.class;
    }
}
