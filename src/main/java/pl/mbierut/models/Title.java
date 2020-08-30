package pl.mbierut.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "titles")
@Getter
@NoArgsConstructor
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "year", nullable = false)
    private int year;

    public Title(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}
