package com.ningxia.ensure.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    /**
     * 获取配置信息
     */
    public static String getText(String key) {
        InputStream in = PropertiesReader.class.getResourceAsStream("/application.properties");
        Properties prop = new Properties();
        String properties = "";
        try {
            prop.load(in);
            properties = prop.getProperty(key).toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return properties;
    }
}
