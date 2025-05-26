package com.aitesting.config;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class TestConfig {
    private static final Properties properties = new Properties();
    private static TestConfig instance;

    private TestConfig() {
        loadProperties();
    }

    public static TestConfig getInstance() {
        if (instance == null) {
            instance = new TestConfig();
        }
        return instance;
    }

    private void loadProperties() {
        try {
            String env = System.getProperty("env", "qa");
            String configFile = "src/test/resources/config/" + env + ".properties";
            properties.load(new FileInputStream(configFile));
            log.info("Loaded configuration for environment: {}", env);
        } catch (IOException e) {
            log.error("Failed to load properties file", e);
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getBaseUrl() {
        return getProperty("base.url");
    }

    public String getBrowser() {
        return getProperty("browser");
    }

    public int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait"));
    }

    public int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }
} 