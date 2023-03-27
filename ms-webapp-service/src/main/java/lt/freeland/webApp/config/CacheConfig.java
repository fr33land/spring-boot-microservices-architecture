package lt.freeland.webApp.config;

import java.util.Collections;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author freeland
 */
@Configuration
public class CacheConfig {

    @Bean
    public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
        return (ConcurrentMapCacheManager cacheManager) -> {
            cacheManager.setAllowNullValues(false);
            cacheManager.setCacheNames(Collections.singletonList("countries"));
        };
    }

}
