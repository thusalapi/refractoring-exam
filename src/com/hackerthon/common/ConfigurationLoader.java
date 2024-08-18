package com.hackerthon.common;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.Properties;

/**
 * Class responsible for loading configuration properties.
 */
public class ConfigurationLoader {

    private static final Logger LOGGER = Logger.getLogger(Clitu.class.getName());
    public static final Properties configProperties = new Properties();

    static {
        try {
            configProperties.load(Qlitu.class.getResourceAsStream("/config/config.properties"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load configuration properties", e);
        }
    }
}