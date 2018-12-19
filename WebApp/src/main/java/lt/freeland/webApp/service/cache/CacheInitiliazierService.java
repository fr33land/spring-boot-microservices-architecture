package lt.freeland.webApp.service.cache;

import javax.annotation.PostConstruct;
import lt.freeland.webApp.service.UtilsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author freeland
 */
@Service
public class CacheInitiliazierService {
    
    private final static Logger LOG = LoggerFactory.getLogger(CacheInitiliazierService.class);
    private final UtilsService utilsService;
    private final CacheManager cacheManager;

    @Autowired
    public CacheInitiliazierService(UtilsService utilsService, CacheManager cacheManager) {
        this.utilsService = utilsService;
        this.cacheManager = cacheManager;
    }
    
    @PostConstruct
    public void initCaches(){
        initCountriesCache();
    }
    
    private void initCountriesCache(){
        LOG.info("Initializing countries cache");
        utilsService.getAllCountries().forEach(
                c -> cacheManager.getCache("countries").put(c.getId(), c)
        );
        LOG.info("Countries cache initialized");
    }
}
