package app.repository;

import java.util.List;

import app.domain.Code;

public interface CodeRepository {
	List <Code> getByCountry(String country);
	void refreshCashe();
}
