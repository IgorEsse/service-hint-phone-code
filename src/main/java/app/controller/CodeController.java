package app.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.domain.Code;

@RestController
public class CodeController {

    @RequestMapping("/rest/code")
    public ResponseEntity hintPhoneCode(@RequestParam(value="country", required=false) String country) {
        
    	List<Code> hintList = new ArrayList<Code>();
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
    	
        while (iterator.hasNext()) {
            String key = iterator.next();
            Code code = new Code();
            code.setCode(key);
            code.setCountry(namesJSON.getString(key));
            code.setName(phoneJSON.getString(key));
            if (country.equals(key))
            	hintList.add(code);
        }
        
        return ResponseEntity.ok(hintList);
    }
}