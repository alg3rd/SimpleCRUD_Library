package com.websystique.library.Author;

import com.websystique.library.Book.Book;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmpty
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "patronymic", nullable = false)
    private String patronymic = "";

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "book_author", joinColumns = {
            @JoinColumn(name = "author_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "book_id",
                    nullable = false, updatable = false) })
    private Set<Book> books = new HashSet<>(0);

    public Set<Book> getBooks() {
        return this.books;
    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Author reader = (Author) o;
//
//        if (id != null ? !id.equals(reader.id) : reader.id != null) return false;
//
//        return title != null ? !title.equals(reader.title) : reader.title != null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result;
//        result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (title != null ? title.hashCode() : 0);
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "Reader [id=" + id + ", title=" + title + "]";
//    }
}
