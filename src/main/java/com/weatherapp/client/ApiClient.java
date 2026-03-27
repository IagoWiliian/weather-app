package com.weatherapp.client;

import com.weatherapp.util.HttpUtil;

public class ApiClient {
    public String fetch(String url) throws Exception {
        return HttpUtil.get(url);
    }
}
