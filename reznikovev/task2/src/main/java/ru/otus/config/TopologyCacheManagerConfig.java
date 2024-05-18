/*
 * (c) 2020 Mobile TeleSystems PJSC, Russia
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Mobile TeleSystems PJSC ("MTS" - NYSE:MBT; MOEX:MTSS)
 * and its suppliers, if any. The intellectual and technical concepts
 * contained herein are proprietary to Mobile TeleSystems PJSC
 * and its suppliers, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Mobile TeleSystems PJSC.
 */
package ru.otus.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class TopologyCacheManagerConfig {

    public static final String USER_NAME = "userName";


    @ConditionalOnProperty(
            value = "user-cache.type",
            havingValue = "caffeine",
            matchIfMissing = true)
    @Bean
    @Primary
    public CacheManager caffeineCacheManager(@Value("${user-cache.caffeine.maximumSize}") long maximumSize,
                                             @Value("${user-cache.caffeine.expireAfterAccessInMinutes}") long expireAfterAccessInMinutes) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setAllowNullValues(false);
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterAccess(expireAfterAccessInMinutes, TimeUnit.MINUTES)
                .maximumSize(maximumSize)
                .recordStats());
        cacheManager.setCacheNames(List.of(USER_NAME));
        return cacheManager;
    }

    @ConditionalOnProperty(
            value = "user-cache.type",
            havingValue = "concurrent-map",
            matchIfMissing = false)
    @Bean
    @Primary
    public CacheManager concurrentMapCacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        cacheManager.setAllowNullValues(false);
        cacheManager.setCacheNames(List.of(USER_NAME));
        return cacheManager;
    }
}