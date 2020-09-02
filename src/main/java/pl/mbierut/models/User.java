package pl.mbierut.models;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    private String lastName;

    @Column(name = "date_created", nullable = false)
    private LocalDate accountCreationDate;

    public User() {
        this.accountCreationDate = LocalDate.now();
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountCreationDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + " created on " + this.accountCreationDate;
    }
}
