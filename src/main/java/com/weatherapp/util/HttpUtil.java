package com.weatherapp.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
    public static String get(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        int status = conn.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                status >= 200 && status < 400 ? conn.getInputStream() : conn.getErrorStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();

        if (status < 200 || status >= 400) {
            throw new RuntimeException("HTTP error code: " + status + " -> " + content.toString());
        }

        return content.toString();
    }
}
