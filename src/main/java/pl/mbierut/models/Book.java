package pl.mbierut.models;

import lombok.Getter;
import lombok.Setter;
import pl.mbierut.enums.BookStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "titles_id")
    @NotNull
    private Title title;

    @Column(name = "status", nullable = false)
    private BookStatus status;

    public Book() {
        this.status = BookStatus.IN_STOCK;
    }

    public Book(Title title) {
        this.title = title;
        this.status = BookStatus.IN_STOCK;
    }

    @Override
    public String toString() {
        return this.title.toString() + " " + this.status;
    }
}
