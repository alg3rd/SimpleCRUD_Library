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

    public Reader findOne(Long id) {
        return repository.findOne(id);
    }
    public Reader findByName(String name) {
        return repository.findByLastName(name);
    }

    public void save(Reader reader) {
        repository.save(reader);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Reader> findAll() {
        return repository.findAll();
    }

    public boolean isUserExist(Reader reader) {
        return findByName(reader.getFirstName()) != null;
    }

}
