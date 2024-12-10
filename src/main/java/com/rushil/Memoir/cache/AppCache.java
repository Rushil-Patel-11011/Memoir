package com.rushil.Memoir.cache;

import com.rushil.Memoir.entity.ConfigMemoirAppEntity;
import com.rushil.Memoir.repository.ConfigMemoirAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class AppCache {
    public Map<String, String> appCache;
    public enum keys {
        WEATHER_API;
    }
    @Autowired
    private ConfigMemoirAppRepository configMemoirAppRepository;

    @PostConstruct
    public void init()
    {
        appCache = new HashMap<>();
        List<ConfigMemoirAppEntity> all = configMemoirAppRepository.findAll();
        for (ConfigMemoirAppEntity configMemoirAppEntity : all) {
            appCache.put(configMemoirAppEntity.getKey(), configMemoirAppEntity.getValue());
        }
    }
}
