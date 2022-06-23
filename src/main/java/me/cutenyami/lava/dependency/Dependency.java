package me.cutenyami.lava.dependency;

public class Dependency {
    private final String url;

    private final String groupId;

    private final String artifactId;

    private final String version;

    public Dependency(String url, String groupId, String artifactId, String version) {
        this.url = url;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public String toUrl() {
        return String.format("%s%s/%s", this.url, String.format("%s/%s", this.groupId, this.artifactId).replace(".", "/") + "/" + this.version, toJar());
    }

    public String toJar() {
        return String.format("%s-%s.jar", this.artifactId, this.version);
    }

    public String getUrl() {
        return this.url;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getArtifactId() {
        return this.artifactId;
    }

    public String getVersion() {
        return this.version;
    }
}

