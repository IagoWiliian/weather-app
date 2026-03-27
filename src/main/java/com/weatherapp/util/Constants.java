package com.weatherapp.util;

import java.io.InputStream;
import java.util.Properties;

public class Constants {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = Constants.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) {
                props.load(is);
            }
        } catch (Exception ignored) {
        }
    }

    public static String getApiKey() {
        return props.getProperty("api.key", "");
    }

    public static String getApiUrlTemplate() {
        return props.getProperty("api.url", "");
    }

    public static String buildUrlForCity(String city) {
        String tpl = getApiUrlTemplate();
        String key = getApiKey();
        return tpl.replace("{city}", urlEncode(city)).replace("{key}", key).replace("{appid}", key);
    }

    private static String urlEncode(String s) {
        try {
            return java.net.URLEncoder.encode(s, java.nio.charset.StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            return s;
        }
    }
}
