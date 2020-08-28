package pl.mbierut.models;

import pl.mbierut.enums.BookStatus;
import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "titles_id")
    private Title title;

    @Column(name = "status", nullable = false)
    private BookStatus status;

    public Book() {
        this.status = BookStatus.IN_STOCK;
    }
}
