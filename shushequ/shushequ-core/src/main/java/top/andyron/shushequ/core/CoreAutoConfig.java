package top.andyron.shushequ.core;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import top.andyron.shushequ.core.cache.RedisClient;
import top.andyron.shushequ.core.config.ProxyProperties;
import top.andyron.shushequ.core.net.ProxyCenter;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author andyron
 * @date 2026/4/16
 */
@Configuration
@EnableConfigurationProperties(ProxyProperties.class)
@ComponentScan(basePackages = "top.andyron.shushequ.core")
public class CoreAutoConfig {
    @Autowired
    private ProxyProperties proxyProperties;

    public CoreAutoConfig(RedisTemplate<String, String> redisTemplate) {
        RedisClient.register(redisTemplate);
    }

    /**
     * 定义缓存管理器，配合Spring的 @Cache 来使用
     *
     */
    @Bean("caffeineCacheManager")
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(CacheProperties.Caffeine.newBuilder().
                // 设置过期时间，写入后五分钟国企
                        expireAfterWrite(5, TimeUnit.MINUTES)
                // 初始化缓存空间大小
                .initialCapacity(100)
                // 最大的缓存条数
                .maximumSize(200)
        );
        return cacheManager;
    }

    @PostConstruct
    public void init() {
        // 这里借助手动解析配置信息，并实例化为Java POJO对象，来实现代理池的初始化
        ProxyCenter.initProxyPool(proxyProperties.getProxy());
    }
}
