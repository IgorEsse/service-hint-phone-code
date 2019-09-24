package app.service;

import app.config.CacheEventLogger;
import app.repository.CodeRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImplementation implements CacheService{

	private static final Logger log = LoggerFactory.getLogger(CacheEventLogger.class);
	
    @Autowired
    CodeRepository codeRep;

	@Override
	public void refreshCache() {
		codeRep.refreshCashe();
		log.info("CacheService произвел обновление кэша");
	}

}
