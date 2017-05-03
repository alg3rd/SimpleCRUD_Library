package com.websystique.library.Reader;


import java.util.List;

public interface Service {
	
	Reader findById(Long id);

	Reader findByName(String name);

	void saveUser(Reader reader);

	void updateUser(Reader reader);

	void deleteUserById(Long id);

	void deleteAllUsers();

	List<Reader> findAllUsers();

	boolean isUserExist(Reader reader);
}