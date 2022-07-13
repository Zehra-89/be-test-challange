package com.freenow.testchallenge.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class APIConfigProvider {
    private static final Logger log = LogManager.getLogger(APIConfigProvider.class);
    private static APIConfigProvider configProvider;

    private APIConfigProvider() {
    }

    public static synchronized APIConfigProvider getApplicationConfigProvider() {
        if (configProvider == null) {
            configProvider = new APIConfigProvider();
        }
        return configProvider;
    }

    @Nullable
    public Properties loadProperties(String fileName) throws IOException {
        log.info("Loading properties for file:" + fileName);
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        Properties properties;
        if (inputStream != null) {
            properties = new Properties();
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("Property file '" + fileName + "' not found in the classpath");
        }
        log.info("Successfully loaded properties for file: " + fileName);
        return properties;
    }

}