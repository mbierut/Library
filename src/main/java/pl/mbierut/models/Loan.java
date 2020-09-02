package pl.mbierut.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Getter
@Setter
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "books_id")
    @NotNull(message = "Must choose a book")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "users_id")
    @NotNull(message = "Must choose a user")
    private User user;

    @Column(name = "loan_date", nullable = false)
    private LocalDate loanDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    public Loan(Book book, User user) {
        this.book = book;
        this.user = user;
        this.loanDate = LocalDate.now();
    }

    public Loan() {
        this.loanDate = LocalDate.now();
    }

    public void returnBook() {
        this.setReturnDate(LocalDate.now());
    }
}
