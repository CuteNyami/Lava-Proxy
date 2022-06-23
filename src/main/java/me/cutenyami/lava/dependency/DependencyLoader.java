package me.cutenyami.lava.dependency;

import me.cutenyami.lava.json.JsonDocument;
import me.cutenyami.lava.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class DependencyLoader {
    private JsonDocument document;

    private final File folder = new File("dependencies");

    private List<Dependency> dependencies = new ArrayList<>();

    public DependencyLoader() {
        try {
            String lines = FileUtil.loadLines("dependencies.json");
            this.document = new JsonDocument(lines);
            this.dependencies = this.document.getList("dependencies", Dependency.class);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void download(Dependency dependency) {
        if ((new File("dependencies/" + dependency.toJar())).exists()) return;
        System.out.println("Dependency " + dependency.getArtifactId() + " downloading...");
        try (InputStream input = URI.create(dependency.toUrl()).toURL().openStream()) {
            Files.copy(input, Paths.get("dependencies/" + dependency.toJar()), StandardCopyOption.REPLACE_EXISTING);
            input.close();
            System.out.println("Dependency " + dependency.getArtifactId() + " downloaded.");
        } catch (IOException e) {
            System.err.println("Dependency " + dependency.getArtifactId() + " failed download!");
        }
    }

    public void init(Dependency dependency) {
        try {
            File file = new File("dependencies/" + dependency.toJar());
            URL url = file.toURI().toURL();
            URLClassLoader loader = (URLClassLoader)ClassLoader.getSystemClassLoader();
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);
            method.invoke(loader, url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public JsonDocument getDocument() {
        return this.document;
    }

    public List<Dependency> getDependencies() {
        return this.dependencies;
    }
}
