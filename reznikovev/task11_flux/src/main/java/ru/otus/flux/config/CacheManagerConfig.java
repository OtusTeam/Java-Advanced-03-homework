package ru.otus.flux.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheManagerConfig {

    public static final String USER_CACHE_NAME = "userName";
    public static final String USER_CACHE = "userCache";


    @Bean
    public CacheManager caffeineCacheManager(@Value("${user-cache.caffeine.maximumSize}") long maximumSize,
                                             @Value("${user-cache.caffeine.expireAfterAccessInMinutes}") long expireAfterAccessInMinutes) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setAllowNullValues(false);
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterAccess(expireAfterAccessInMinutes, TimeUnit.MINUTES)
                .maximumSize(maximumSize)
                .recordStats());
        cacheManager.setCacheNames(List.of(USER_CACHE_NAME));
        return cacheManager;
    }

    @Bean(USER_CACHE)
    public Cache userLoginCache(CacheManager cacheManager) {
        return cacheManager.getCache(USER_CACHE_NAME);
    }

}