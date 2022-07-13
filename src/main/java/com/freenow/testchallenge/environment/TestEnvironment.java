package com.freenow.testchallenge.environment;

import com.freenow.testchallenge.config.APIConfigProvider;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class TestEnvironment {

    private static final Logger log = LogManager.getLogger(TestEnvironment.class);

    @Getter
    private String baseUrl;
    @Getter
    private String postsEndpoint;
    @Getter
    private String commentsEndpoint;
    @Getter
    private String usersEndpoint;
    private static TestEnvironment testEnvironment;
    private final Properties properties;

    public static TestEnvironment getEnvironment(String environment) {
        if (testEnvironment == null) {
            testEnvironment = new TestEnvironment(environment);
        }
        return testEnvironment;
    }

    private TestEnvironment(String environment) {
        log.info("Setting up environment for: " + environment);
        properties = getPropertiesForEnvironment(environment);
        if (properties == null) {
            log.error("The test environment '" + environment + "' was not found. Please provide a valid test environment name.");
            return;
        }

        log.info("Loading environment variables:");
        baseUrl = getValueFor(EnvironmentKeys.BSE_URL);
        usersEndpoint = getValueFor(EnvironmentKeys.USER_ENDPOINT);
        postsEndpoint = getValueFor(EnvironmentKeys.POSTS_ENDPOINT);
        commentsEndpoint = getValueFor(EnvironmentKeys.COMMENTS_ENDPOINT);

        log.info("Environment successfully setup");
    }

    private Properties getPropertiesForEnvironment(String environment) {
        Properties _properties;
        String fileName = environment + ".properties";
        log.info("Environment file: " + fileName);
        try {
            _properties = APIConfigProvider.getApplicationConfigProvider().loadProperties(fileName);
        } catch (IOException exception) {
            log.error("Something went wrong for while getting config data from properties file" + exception.getMessage());
            _properties = null;
        }
        return _properties;
    }

    private String getValueFor(String key) {
        String value = properties.getProperty(key);
        log.info(key + ":" + value);
        return value;
    }
}
