package app.service;

import java.util.List;

import app.domain.Code;

public interface CodeService {
	List<Code> getByCountry(String country);
}