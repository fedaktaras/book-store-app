package com.example.bookstoreapp;

import java.math.BigDecimal;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.servive.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreAppApplication {

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreAppApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book dune = new Book();
            dune.setTitle("Dune");
            dune.setAuthor("Frank Herbert");
            dune.setIsbn("987654321");
            dune.setPrice(BigDecimal.valueOf(300));

            bookService.save(dune);
            System.out.println(bookService.findAll());
        };
    }
}
