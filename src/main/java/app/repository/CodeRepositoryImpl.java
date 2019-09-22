package app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import org.json.JSONObject;

import app.domain.Code;
import app.json.JsonHelper;

@Component
public class CodeRepositoryImpl implements CodeRepository{

	@Autowired
	JsonHelper helper;
	
	private String urlNames = "http://country.io/names.json";
	private String urlPhone = "http://country.io/phone.json";
	
	@Override
	public List<Code> getByCountry(String country) {
    	
		JSONObject namesJSON = new JSONObject(helper.getJsonByUrl(urlNames));
        JSONObject phoneJSON = new JSONObject(helper.getJsonByUrl(urlPhone));
        Iterator<String> iterator = namesJSON.keys();
		
        List<Code> hintList = new ArrayList<Code>();
    	
        while (iterator.hasNext()) {
            String key = iterator.next();
            Code code = new Code();
            code.setName(key);
            code.setCountry(namesJSON.getString(key));
            code.setCode(phoneJSON.getString(key));
            if (code.getCountry().toLowerCase().indexOf(country.toLowerCase()) == 0)
            	hintList.add(code);
        }
        return hintList;
	}

}
