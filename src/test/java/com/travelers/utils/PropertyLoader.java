package com.travelers.utils;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class PropertyLoader {

    public static Configuration loadProperties() throws ConfigurationException {
        Configurations configs = new Configurations();
        return configs.properties("config.properties");
    }
}
