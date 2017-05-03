package com.algerd.library.repositories;

import com.algerd.library.Reader.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    Reader findByLastName(String name);
}
