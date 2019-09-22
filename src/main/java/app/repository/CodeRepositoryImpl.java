package app.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import app.domain.Code;

@Component
public class CodeRepositoryImpl implements CodeRepository{

	public List<Code> getByCountry(String country) {
		HttpURLConnection connNames;
    	HttpURLConnection connPhone;
    	String strNames = null;
    	String strPhone = null;
    	
    	try {
			URL namesURL= new URL("http://country.io/names.json");
			URL phoneURL= new URL("http://country.io/phone.json");
			connNames = (HttpURLConnection) namesURL.openConnection();
			connNames.setRequestMethod("GET");
			connNames.disconnect();
			strNames = IOUtils.toString(connNames.getInputStream(), StandardCharsets.UTF_8);
			connPhone = (HttpURLConnection) phoneURL.openConnection();
			connPhone.setRequestMethod("GET");
			strPhone = IOUtils.toString(connPhone.getInputStream(), StandardCharsets.UTF_8);
			connPhone.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		JSONObject namesJSON = new JSONObject(strNames);
        JSONObject phoneJSON = new JSONObject(strPhone);
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
