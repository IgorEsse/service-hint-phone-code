package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.Code;
import app.repository.CodeRepository;

@Service
public class CodeServiceImpl implements CodeService{

    @Autowired
    CodeRepository codeRep;
	
	@Override
	public List<Code> getByCountry(String country) {
		
        List<Code> hintList = codeRep.getByCountry(country);
    	
        return hintList;
	}

}
