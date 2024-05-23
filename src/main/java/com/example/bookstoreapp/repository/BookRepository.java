package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Book;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b JOIN b.categories c "
            + "WHERE c.id = :categoryId AND b.isDeleted = false")
    List<Book> findAllByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

    @EntityGraph(value = "book-with-categories", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Book> findById(Long id);

    @EntityGraph(value = "book-with-categories", type = EntityGraph.EntityGraphType.LOAD)
    Page<Book> findAll(Pageable pageable);

    @Query("SELECT b.price FROM Book b WHERE b.id = :bookId")
    BigDecimal getPriceByBookId(@Param("bookId") Long bookId);
}
