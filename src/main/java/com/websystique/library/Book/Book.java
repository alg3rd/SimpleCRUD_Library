package com.websystique.library.Book;

import com.websystique.library.Author.Author;
import com.websystique.library.Genre.Genre;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "rentCost", nullable = false)
    private double rentCost = 0.0;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
    private Set<Author> authors = new HashSet<>(0);

    @OneToMany
    @JoinTable(
            name="book_genres",
            joinColumns = @JoinColumn( name="book_id"),
            inverseJoinColumns = @JoinColumn( name="genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book reader = (Book) o;

        if (id != null ? !id.equals(reader.id) : reader.id != null) return false;

        return title != null ? !title.equals(reader.title) : reader.title != null;
    }

    @Override
    public int hashCode() {
        int result;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Reader [id=" + id + ", title=" + title + "]";
    }

    public Set<Author> getStocks() {
        return this.authors;
    }
}
