package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.domain.Code;
import app.service.CodeService;

@RestController
@RequestMapping("/rest/code")
public class CodeController {
	
    @Autowired
    CodeService codeService;

    @GetMapping
    public ResponseEntity hintPhoneCode(@RequestParam(value="country") String country)  throws Exception{
        
    	List<Code> hintList = codeService.getByCountry(country);

        return ResponseEntity.ok(hintList);
    }
}