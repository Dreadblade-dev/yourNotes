package com.dreadblade.servlets.email.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailProperties {
    private static Logger logger = Logger.getLogger(MailProperties.class.getName());
    private static final String PROPERTIES_FILE = "mail.properties";
    private static Properties properties;
    private static boolean isLoaded = false;

    public MailProperties() {
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
            throw new NoMailPropertiesException("Cannot load properties file " + PROPERTIES_FILE + ".");
        }
    }

    public static Properties getProperties() {
        if (!isLoaded)
            new MailProperties();

        return properties;
    }
}