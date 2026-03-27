package com.weatherapp.util;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {
    @Test
    public void parseSimpleJson() {
        String json = "{\"hello\":\"world\", \"num\": 10}";
        Map<String, Object> m = JsonParser.parseToMap(json);
        assertEquals("world", m.get("hello"));
        assertTrue(m.containsKey("num"));
    }
}
