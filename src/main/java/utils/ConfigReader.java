package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ConfigReader {
    private static final Properties props = new Properties();

    static {
        String projectPath = System.getProperty("user.dir");
        String configPath = projectPath + "/src/test/java/resources/config.properties";

        try (FileInputStream fis = new FileInputStream(configPath)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not find or load config.properties at: " + configPath, e);
        }
    }

   

    public static String get(String key) {
        String override = System.getProperty(key);
        if (override != null && !override.isEmpty()) return override;
        String val = props.getProperty(key);
        if (val == null) throw new IllegalArgumentException("Missing config key: " + key);
        return val.trim();
    }
}