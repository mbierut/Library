package pl.mbierut.services;

import org.springframework.stereotype.Service;
import pl.mbierut.models.Book;
import pl.mbierut.models.Loan;
import pl.mbierut.models.User;

@Service
public class LoanService {

    private Loan loanABook(Book book, User user){
        return new Loan(book, user);
    }
}
