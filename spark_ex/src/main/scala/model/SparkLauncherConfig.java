package model;

import java.util.Map;

public class SparkLauncherConfig {
    private int id;
    private String desc;
    private String appName;
    private Map<String, String> properties;
    private String created;
    private String modified;

    public SparkLauncherConfig(int id, String desc, String appName, Map<String, String> properties, String created, String modified) {
        this.id = id;
        this.desc = desc;
        this.appName = appName;
        this.properties = properties;
        this.created = created;
        this.modified = modified;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "SparkLauncherConfig{" +
                "id=" + this.id +
                ", desc='" + this.desc + '\'' +
                ", appName='" + this.appName + '\'' +
                ", properties=" + this.properties +
                ", created='" + this.created + '\'' +
                ", modified='" + this.modified + '\'' +
                '}';
    }
}
