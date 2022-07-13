package com.freenow.testchallenge.listener;

import com.freenow.testchallenge.environment.TestEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class TestSuiteListener implements ISuiteListener {

    private static final Logger log = LogManager.getLogger(TestSuiteListener.class);

    @Override
    public void onStart(ISuite suite) {
        log.info("Testsuite Started");
        TestEnvironment.initialize(System.getProperty("environment"));
    }

    @Override
    public void onFinish(ISuite suite) {
        log.info("Testsuite Completed");
    }


}
