package me.cutenyami.lava.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JsonDocument implements IDocument<JsonDocument> {
    private final Gson gson;

    private final JsonObject object;

    public JsonDocument(Gson gson, JsonObject object) {
        this.gson = gson;
        this.object = object;
    }

    public JsonDocument(JsonObject object) {
        this(new Gson(), object);
    }

    public JsonDocument(String jsonString) {
        this.gson = new Gson();
        this.object = (JsonObject)this.gson.fromJson(jsonString, JsonObject.class);
    }

    public JsonDocument(Gson gson, String jsonString) {
        this(gson, (JsonObject)gson.fromJson(jsonString, JsonObject.class));
    }

    public JsonDocument(Gson gson) {
        this(gson, new JsonObject());
    }

    public JsonDocument() {
        this(new Gson(), new JsonObject());
    }

    public JsonDocument append(String key, Object value) {
        this.object.add(key, this.gson.toJsonTree(value));
        return this;
    }

    public JsonDocument depend(String key) {
        this.object.remove(key);
        return this;
    }

    public <T> T get(String key, Class<T> clazz) {
        return (T)this.gson.fromJson(this.object.get(key), clazz);
    }

    public <T> List<T> getList(String key, Class<T> clazz) {
        return (List<T>)this.gson.fromJson(this.object.get(key), TypeToken.getParameterized(List.class, new Type[] { clazz }).getType());
    }

    public <T1, T2> Map<T1, T2> getMap(String key, Class<T1> clazz1, Class<T2> clazz2) {
        return (Map<T1, T2>)this.gson.fromJson(this.object.get(key), TypeToken.getParameterized(Map.class, new Type[] { clazz1, clazz2 }).getType());
    }

    public String toString() {
        return this.gson.toJson((JsonElement)this.object);
    }
}
