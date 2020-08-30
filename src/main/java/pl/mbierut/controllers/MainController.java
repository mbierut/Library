package pl.mbierut.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String sendHome() {
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
    public String addNewTitle(@RequestParam String title, @RequestParam String author, @RequestParam int year) {
        Title newTitle = new Title(title, author, year);
        this.titleRepository.save(newTitle);
        return "redirect:.";
    }

    @GetMapping("/books")
    public String showBooks() {
        this.bookRepository.findAll();
        return "book-list";
    }

    @GetMapping("/books/add")
    public String createNewBook() {
        return "add-book";
    }

    @PostMapping("/books/add")
    public String addNewBook(@RequestParam Title title) {
        Book newBook = new Book(title);
        this.bookRepository.save(newBook);
        return "redirect:.";
    }

    @GetMapping("/books/change-status")
    public String changeBookStatus(Long id, Model model) {
        Book book = this.bookRepository.findById(id).get();
        model.addAttribute("book", book);
        return "change-book-status";
    }

    @PostMapping("/books/change-status")
    public String updateArticle(@ModelAttribute Book book) {
        this.bookRepository.save(book);
        return "redirect:.";
    }


    //Loans

    @GetMapping("/loans")
    public String showLoans() {
        this.loanRepository.findAll();
        return "loan-list";
    }

    @GetMapping("/loans/loan-book")
    public String createNewLoan() {
        return "loan-book";
    }

    @PostMapping
    public String loanBook(@RequestParam Book book, @RequestParam User user) {
        Loan newLoan = new Loan(book, user);
        this.loanRepository.save(newLoan);
        return "loan-book";
    }

    @GetMapping("/loans/return-book")
    public String returnBook(Long id) {
        Loan loan = this.loanRepository.findById(id).get();
        loan.returnBook();
        return "redirect:.";
    }


}
