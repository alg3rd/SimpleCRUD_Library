package com.websystique.library.Reader;

import java.util.List;

import com.websystique.library.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@org.springframework.stereotype.Service("userService")
@Transactional
public class ServiceImpl implements Service {

    @Autowired
    private ReaderRepository repository;

    public Reader findById(Long id) {
        return repository.findOne(id);
    }

    public Reader findByName(String name) {
        return repository.findByLastName(name);
    }

    public void saveUser(Reader reader) {
        repository.save(reader);
    }

    public void updateUser(Reader reader) {
        saveUser(reader);
    }

    public void deleteUserById(Long id) {
        repository.delete(id);
    }

    public void deleteAllUsers() {
        repository.deleteAll();
    }

    public List<Reader> findAllUsers() {
        return repository.findAll();
    }

    public boolean isUserExist(Reader reader) {
        return findByName(reader.getFirstName()) != null;
    }

}
