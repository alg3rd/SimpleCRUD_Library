package com.websystique.library.Book;

import com.websystique.library.Genre.Genre;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

//@Entity
//@Table(name = "book")
public class BookTemp implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotEmpty
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @OneToMany
//    @JoinTable(
//            name = "book_authors",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "author_id")
//    )
//    private Set<Author> authors;
//
//    @OneToMany
//    @JoinTable(
//            name = "book_genres",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "genre_id")
//    )
//    private Set<Genre> genres;
}
