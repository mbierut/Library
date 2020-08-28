package pl.mbierut.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mbierut.models.Book;
import pl.mbierut.models.Title;
import pl.mbierut.models.User;
import pl.mbierut.repositories.BookRepository;
import pl.mbierut.repositories.LoanRepository;
import pl.mbierut.repositories.TitleRepository;
import pl.mbierut.repositories.UserRepository;

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
    public String userHome() {
        return "user";
    }

    @GetMapping("users/list")
    public String userList() {
        this.userRepository.findAll();
        return "users";
    }

    @GetMapping("users/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("users/add")
    public String addUser(@ModelAttribute User user) {
        this.userRepository.save(user);
        return "add-user";
    }


    //Books

    @RequestMapping("/titles")
    public String showTitles() {
        this.titleRepository.findAll();
        return "titles";
    }

    @GetMapping("titles/add")
    public String addTitle(Model model) {
        model.addAttribute("title", new Title());
        return "add-title";
    }

    @PostMapping("titles/add")
    public String addTitle(@ModelAttribute Title title) {
        this.titleRepository.save(title);
        return "add-title";
    }

    @GetMapping("books")
    public String showBooks() {
        this.bookRepository.findAll();
        return "books";
    }

    @GetMapping("books/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("books/add")
    public String addBook(@ModelAttribute Book book) {
        this.bookRepository.save(book);
        return "redirect:./books";
    }

    @GetMapping("books/change-status")
    public String changeBookStatus(Long id, Model model) {
        Book book = this.bookRepository.findById(id).get();
        model.addAttribute("book", book);
        return "change-book-status";
    }

    @PostMapping("books/change-status")
    public String updateArticle(@ModelAttribute Book book) {
        this.bookRepository.save(book);
        return "redirect:./books";
    }


    //Loans

    @GetMapping("/loans")
    public String showLoans() {
        this.loanRepository.findAll();
        return "loans";
    }

    @GetMapping("/loans/loan-book")
    public String loanBook() {
        return "loan-book";
    }

    @GetMapping("/loans/return-book")
    public String returnBook() {
        return "redirect:./loans";
    }


}
