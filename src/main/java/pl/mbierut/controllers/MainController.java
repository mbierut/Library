package pl.mbierut.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mbierut.enums.BookStatus;
import pl.mbierut.models.Book;
import pl.mbierut.models.Loan;
import pl.mbierut.models.Title;
import pl.mbierut.models.User;
import pl.mbierut.repositories.BookRepository;
import pl.mbierut.repositories.LoanRepository;
import pl.mbierut.repositories.TitleRepository;
import pl.mbierut.repositories.UserRepository;

import java.util.List;

@Controller
public class MainController {
    private UserRepository userRepository;
    private TitleRepository titleRepository;
    private BookRepository bookRepository;
    private LoanRepository loanRepository;

    private MainController(UserRepository userRepository, TitleRepository titleRepository,
                           BookRepository bookRepository, LoanRepository loanRepository) {
        this.userRepository = userRepository;
        this.titleRepository = titleRepository;
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // User

    @GetMapping("/users")
    public String userHome(Model model) {
        List<User> userList = this.userRepository.findAll();
        model.addAttribute("userList", userList);
        return "user-list";
    }

    @GetMapping("/users/add")
    public String createNewUser() {
        return "add-user";
    }

    @PostMapping("/users/add")
    public String addNewUser(@RequestParam String firstName, @RequestParam String lastName) {
        User newUser = new User(firstName, lastName);
        this.userRepository.save(newUser);
        return "redirect:.";
    }


    //Books

    @RequestMapping("/titles")
    public String showTitles(Model model) {
        List<Title> titleList = this.titleRepository.findAll();
        model.addAttribute("titleList", titleList);
        return "title-list";
    }

    @GetMapping("/titles/add")
    public String createNewTitle() {
        return "add-title";
    }

    @PostMapping("/titles/add")
    public String addNewTitle(@RequestParam String title, @RequestParam String author, @RequestParam String year) {
        Title newTitle = new Title(title, author, year);
        this.titleRepository.save(newTitle);
        return "redirect:.";
    }

    @GetMapping("/books")
    public String showBooks(Model model) {
        List<Book> bookList = this.bookRepository.findAll();
        model.addAttribute("bookList", bookList);
        return "book-list";
    }

    @GetMapping("/books/title-search")
    public String createNewBook() {
        return "title-search";
    }

    @PostMapping("/books/title-search")
    public String chooseTitleList(@RequestParam String title, @RequestParam String author, Model model) {
        List<Title> titleList = this.titleRepository.findTitlesByTitleContainingAndAuthorContaining(title, author);
        model.addAttribute("titleList", titleList);
        return "title-list";
    }

    @PostMapping("/books/add")
    public String addNewBook(@RequestParam Long id) {
        Title title = this.titleRepository.getOne(id);
        Book newBook = new Book(title);
        this.bookRepository.save(newBook);
        return "redirect:.";
    }

    @GetMapping("/books/change-status/{id}")
    public String changeBookStatus(@PathVariable Long id, @RequestParam String status, Model model) {
        if (this.bookRepository.findById(id).isPresent()) {
            Book book = this.bookRepository.findById(id).get();
            book.setStatus(BookStatus.valueOf(status));
            return "redirect:..";
        }
        return "error";
    }


    //Loans

    @GetMapping("/loans")
    public String showLoans(Model model) {
        List<Loan> loanList = this.loanRepository.findAllByReturnDateNull();
        model.addAttribute("loanList", loanList);
        return "loan-list";
    }

    @GetMapping("loans/history")
    public String showLoanHistory(Model model) {
        List<Loan> loanList = this.loanRepository.findAll();
        model.addAttribute("loanList", loanList);
        return "loan-list";
    }

    @GetMapping("/loans/loan-book")
    public String createNewLoan(Model model) {
        List<Book> bookList = this.bookRepository.findBooksByStatus(BookStatus.IN_STOCK);
        List<User> userList = this.userRepository.findAll();
        model.addAttribute("bookList", bookList).addAttribute("userList", userList);
        return "loan-book";
    }

    @PostMapping("/loans/loan-book")
    public String loanBook(@RequestParam Long bookId, @RequestParam Long userId) {
        if (this.bookRepository.findById(bookId).isPresent() && this.bookRepository.findById(bookId).isPresent()) {
            Book book = this.bookRepository.findById(bookId).get();
            User user = this.userRepository.findById(userId).get();
            book.setStatus(BookStatus.CHECKED_OUT);
            this.bookRepository.save(book);
            Loan newLoan = new Loan(book, user);
            this.loanRepository.save(newLoan);
            return "redirect:.";
        }
        return "error";
    }

    @GetMapping("/loans/return-book")
    public String returnBook(@RequestParam Long id) {
        Loan loan = this.loanRepository.findById(id).get();
        loan.returnBook();
        Book book = loan.getBook();
        book.setStatus(BookStatus.IN_STOCK);
        this.loanRepository.save(loan);
        this.bookRepository.save(book);
        return "redirect:..";
    }


}
