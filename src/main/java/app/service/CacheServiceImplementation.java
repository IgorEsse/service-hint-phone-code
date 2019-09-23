package app.service;

import app.repository.CodeRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImplementation implements CacheService{

    @Autowired
    CodeRepository codeRep;

	@Override
	public void refreshCache() {
		codeRep.refreshCashe();
	}

}
