package lt.freeland.webApp.service.cache;

import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import lt.freeland.common.dto.CountriesDto;
import lt.freeland.webApp.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author freeland
 */
@Service
@Slf4j
public class CacheInitiliazierService {

    private final UtilsService utilsService;
    private final CacheManager cacheManager;

    @Autowired
    public CacheInitiliazierService(UtilsService utilsService, CacheManager cacheManager) {
        this.utilsService = utilsService;
        this.cacheManager = cacheManager;
    }

    @PostConstruct
    public void initCaches() {
        initCountriesCache();
    }

    private void initCountriesCache() {
        log.info("================= Initializing countries cache =================");
        utilsService.getAllCountries().forEach(
                c -> cacheManager.getCache("countries").put(c.getId(), c)
        );
        List<CountriesDto> c = utilsService.getAllCountries();
        log.info("================= Countries cache initialized =================");
    }
}
