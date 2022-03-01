package utils.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class ConfigReader
{
    private static final Logger log = LoggerFactory.getLogger(ConfigReader.class);
    private static ConfigReader configReader;
    private final Properties properties;

    private ConfigReader()
    {
        log.info("Starting to read configurations");
        BufferedReader reader;
        String rootDir = System.getProperty("user.dir");
        String propertyFilePath = rootDir + "/src/test/resources/configuration.properties";
        try
        {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try
            {
                properties.load(reader);
                reader.close();
            }
            catch (IOException e)
            {
                log.info("IO Exception while reading properties::{}", e.getMessage());
            }
        }
        catch (FileNotFoundException e)
        {
            log.info("Configuration file not found at ::{}", propertyFilePath);
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public static ConfigReader getInstance()
    {
        if (configReader == null)
        {
            configReader = new ConfigReader();
        }
        return configReader;
    }

    public String getConfigValue(String key)
    {
        String value = properties.getProperty(key);
        if (value != null)
            return value;
        else
        {
            log.info("Config value not specified in the Configuration.properties file for key {}", key);
            throw new RuntimeException("Config value not specified in the Configuration.properties file.");
        }
    }
}
