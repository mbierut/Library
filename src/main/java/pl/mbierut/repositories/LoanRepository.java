package pl.mbierut.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mbierut.models.Loan;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findAllByReturnDateNull();
}
