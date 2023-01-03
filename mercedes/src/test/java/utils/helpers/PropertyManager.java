package utils.helpers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertyManager {

    private Map<String, Properties> map = new HashMap<>();
    Map<String, File> files = new HashMap<>();

    public PropertyManager() {
        files.put("env", new File(ClassLoader.getSystemResource("properties/env.properties").getPath()));


        for (File f : files.values()) {
            Properties props = new Properties();
            try {
                props.load(new FileReader(f));
                map.put(f.getName(), props);
            } catch (IOException ex) {
                // handle error
            }
        }
    }

    public String getProperty(String file, String key) {
        Properties props = map.get(file);

        if (props != null) {
            return props.getProperty(key);
        }
        return null;
    }

    public Set<String> getAllPropertyNames(String file) {
        Properties props = map.get(file);

        if (props != null) {
            return props.stringPropertyNames();
        }
        return null;
    }

    public boolean containsKey(String file, String key) {
        Properties props = map.get(file);

        if (props != null) {
            return props.contains(key);
        }

        return false;
    }

    public void setProperty(String file, String key, String value) {
        Properties props = map.get(file);
        props.setProperty(key, value);
    }
}