package com.rushil.Memoir.service;

import com.rushil.Memoir.api.response.WeatherResponse;
import com.rushil.Memoir.cache.AppCache;
import com.rushil.Memoir.constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class WeatherService {
    @Value("${weather_api_key}")
    private String apikey ;

    @Autowired
    private RedisService redisService;
    @Autowired
    private AppCache appCache;
//    private static final String api = "http://api.weatherstack.com/current?access_key=ACCESS_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;
    public WeatherResponse getWeather(String city)
    {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if (weatherResponse!=null)
        {
            return weatherResponse;
        }else
        {
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.API_KEY, apikey).replace(PlaceHolders.CITY, city);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body!=null)
            {
                redisService.set("weather_of_" + city,body,300l);
            }
            return body;
        }

    }
}
