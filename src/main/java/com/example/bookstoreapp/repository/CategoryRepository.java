package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.Category;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.id = :categoryId")
    List<Book> findAllBooksWithCategory(@Param("categoryId") Long categoryId, Pageable pageable);
}
