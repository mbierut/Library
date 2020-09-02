package pl.mbierut.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "titles")
@Getter
@NoArgsConstructor
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    @NotBlank
    private String title;

    @Column(name = "author", nullable = false)
    @NotBlank
    private String author;

    @Column(name = "year", nullable = false)

    private String year;

    public Title(String title, String author, String year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public String toString() {
        return "\"" + this.title + "\" by " + this.author + " (" + this.year + ")";
    }
}
