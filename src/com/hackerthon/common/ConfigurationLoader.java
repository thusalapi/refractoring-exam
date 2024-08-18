package com.hackerthon.common;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.Properties;

/**
 * Class responsible for loading configuration properties.
 */
public class ConfigurationLoader {

    private static final Logger LOGGER = Logger.getLogger(ConfigurationLoader.class.getName());
    public static final Properties configProperties = new Properties();

    static {
        try {
            configProperties.load(ConfigurationLoader.class.getResourceAsStream(Constants.CONFIG_FILE_PATH));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load configuration properties", e);
        }
    }

    // Private constructor to prevent instantiation
    private ConfigurationLoader() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}