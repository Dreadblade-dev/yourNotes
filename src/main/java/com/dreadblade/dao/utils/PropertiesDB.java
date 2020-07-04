package com.dreadblade.dao.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Loads properties from the db.properties file and provides access to them
 */
public class PropertiesDB {
    private static Logger logger = Logger.getLogger(PropertiesDB.class.getName());
    private static final String PROPERTIES_FILE = "db.properties";
    private static final String SPECIFIC_KEY = "db.jdbc.";
    private static Properties properties;
    private static boolean isLoaded = false;

    public PropertiesDB() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream(PROPERTIES_FILE);
            properties = new Properties();
            properties.load(input);
            input.close();
            logger.info("Properties file " + PROPERTIES_FILE + " has loaded successfully!");
            isLoaded = true;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Missing " + PROPERTIES_FILE + " file!");
            throw new NoDBPropertiesException("Cannot load properties file " + PROPERTIES_FILE + ".");
        }
    }

    public String getProperty(String key) {
        String fullKey = SPECIFIC_KEY + key;

        if (properties.containsKey(fullKey)) {
            return properties.getProperty(fullKey);
        } else {
            throw new NoDBPropertiesException("Required property \"" + fullKey + "\" is missing in properties file \"" +
                    PROPERTIES_FILE + "\".");
        }
    }
}
