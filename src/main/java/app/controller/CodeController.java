package app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.config.CacheEventLogger;
import app.domain.Code;
import app.service.CacheService;
import app.service.CodeService;

@RestController
@RequestMapping("/rest/code")
public class CodeController {
	
	private static final Logger log = LoggerFactory.getLogger(CacheEventLogger.class);
	
    @Autowired
    CodeService codeService;
    
    @Autowired
    CacheService cacheService;

	@GetMapping
    public ResponseEntity hintPhoneCode(@RequestParam(value="country") String country)  throws Exception{
		
		log.info("Получен запрос по адресу: /rest/code?country={}",country);
    	List<Code> hintList = codeService.getByCountry(country);

    	log.info("Получены записи: {}",hintList);
        return ResponseEntity.ok(hintList);
    }
    
    @GetMapping("/refresh")
    public ResponseEntity refreshCache() {
    	try {
    		log.info("Получен запрос по адресу: /rest/code/refresh");
    	
    		cacheService.refreshCache();
        
    		log.info("Обновление кеша произведено успешно.");
    		return ResponseEntity.ok("Обновление выполнено");
    	}catch(Exception e) {
    		log.info("При обновлении кеша произошло исключение: {}.", e.getMessage());
    		return ResponseEntity.ok("Обновление не выполнено");
    	}
    }
}