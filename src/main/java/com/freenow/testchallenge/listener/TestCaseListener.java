package com.freenow.testchallenge.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestCaseListener implements ITestListener {

    private static final Logger log = LogManager.getLogger();

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Starting Test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test " + result.getName() + " PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("Test " + result.getName() + " FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Test " + result.getName() + " SKIPPED");
    }
}
