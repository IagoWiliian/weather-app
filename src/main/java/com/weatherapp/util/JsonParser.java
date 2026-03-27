package com.weatherapp.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class JsonParser {
    private static final Gson gson = new Gson();

    public static Map<String, Object> parseToMap(String json) {
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(json, type);
    }
}
