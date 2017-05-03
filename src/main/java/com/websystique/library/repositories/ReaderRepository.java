package com.websystique.library.repositories;

import com.websystique.library.Reader.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    Reader findByLastName(String name);
}
