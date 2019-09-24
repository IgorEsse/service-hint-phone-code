package app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.config.CacheEventLogger;
import app.domain.Code;
import app.repository.CodeRepository;

@Service
public class CodeServiceImpl implements CodeService{

	private static final Logger log = LoggerFactory.getLogger(CacheEventLogger.class);
	
    @Autowired
    CodeRepository codeRep;
	
	@Override
	public List<Code> getByCountry(String country) {
		
        List<Code> hintList = codeRep.getByCountry(country);
        log.info("Сервис CodeService вернул записи: {}",hintList);
        
        return hintList;
	}

}
