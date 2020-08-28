package pl.mbierut.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_created", nullable = false)
    private LocalDate accountCreationDate;

    public User() {
        this.accountCreationDate = LocalDate.now();
    }
}
