package com.freenow.testchallenge.listener;

import com.freenow.testchallenge.api.APISpecification;
import com.freenow.testchallenge.environment.TestEnvironment;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class TestSuiteListener implements ISuiteListener {

    private static final Logger log = LogManager.getLogger();

    @SneakyThrows
    @Override
    public void onStart(ISuite suite) {
        log.info("Testsuite Start");
        TestEnvironment.initialize(System.getProperty("environment"));
        APISpecification.initialize();
    }

    @Override
    public void onFinish(ISuite suite) {
        log.info("Testsuite Completed");
    }


}
