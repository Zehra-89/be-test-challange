package com.freenow.testchallenge.api;

import com.freenow.testchallenge.environment.TestEnvironment;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class APISpecification {

    public static RequestSpecification requestSpec;

    private static final Logger log = LogManager.getLogger();

    public static void initialize() {
        log.info("Initializing request specifications");
        APISpecification.requestSpec = new RequestSpecBuilder()
                .setBaseUri(TestEnvironment.baseUrl)
                .addHeader("Content-Type", "application/json")
                .build();
    }
}
