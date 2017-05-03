package com.websystique.library.Reader;

import java.util.List;

public interface Service {
	
	Reader findOne(Long id);

	Reader findByName(String name);

	void save(Reader reader);

	void delete(Long id);

	void deleteAll();

	List<Reader> findAll();

	boolean isUserExist(Reader reader);
}