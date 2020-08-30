package pl.mbierut.models;

import lombok.Getter;
import lombok.Setter;
import pl.mbierut.enums.BookStatus;
import javax.persistence.*;

@Entity
@Table(name = "books")
@Getter
@Setter
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

    public Book(Title title) {
        this.title = title;
        this.status = BookStatus.IN_STOCK;
    }
}
