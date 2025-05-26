package com.aitesting.tests;

import com.aitesting.config.TestConfig;
import com.aitesting.core.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

@Slf4j
public class BaseTest {
    protected TestConfig config;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        config = TestConfig.getInstance();
        System.setProperty("browser", browser != null ? browser : config.getBrowser());
        log.info("Starting test with browser: {}", System.getProperty("browser"));
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
        log.info("Test completed");
    }
} 