package me.cutenyami.lava.json;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import java.util.Map;

public class JsonFile implements IDocument<JsonDocument> {
    private final Gson gson;

    private IDocument<JsonDocument> document;

    private final File file;

    public JsonFile(Gson gson, String path) {
        this.gson = gson;
        this.document = new JsonDocument(gson);
        this.file = new File(path);
    }

    public JsonFile(String path) {
        this(new Gson(), path);
    }

    public boolean exists() {
        return this.file.exists();
    }

    public void create() {
        try {
            if (this.file.getParentFile() != null)
                Files.createDirectories(this.file.getParentFile().toPath(), (FileAttribute<?>[])new FileAttribute[0]);
            Files.createFile(this.file.toPath(), (FileAttribute<?>[])new FileAttribute[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JsonDocument append(String key, Object value) {
        return this.document.append(key, value);
    }

    public JsonDocument depend(String key) {
        return this.document.depend(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        return this.document.get(key, clazz);
    }

    public <T> List<T> getList(String key, Class<T> clazz) {
        return this.document.getList(key, clazz);
    }

    public <T1, T2> Map<T1, T2> getMap(String key, Class<T1> clazz1, Class<T2> clazz2) {
        return this.document.getMap(key, clazz1, clazz2);
    }

    public void load() {
        try {
            BufferedReader reader = Files.newBufferedReader(this.file.toPath());
            StringBuilder builder = new StringBuilder();
            for (; reader.ready(); builder.append(reader.readLine()));
            reader.close();
            this.document = new JsonDocument(this.gson, builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            BufferedWriter writer = Files.newBufferedWriter(this.file.toPath(), new java.nio.file.OpenOption[0]);
            writer.write(this.document.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return this.file;
    }

    public String toString() {
        return this.document.toString();
    }
}
