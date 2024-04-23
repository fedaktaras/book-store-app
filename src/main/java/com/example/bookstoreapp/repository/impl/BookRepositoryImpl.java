package com.example.bookstoreapp.repository.impl;

import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final EntityManagerFactory entityManagerFactory;

    public BookRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Book save(Book book) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (book.getId() == null) {
                entityManager.persist(book);
            } else {
                entityManager.merge(book);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Can`t create or merge Book", e);
        } finally {
            entityManager.close();
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("FROM Book", Book.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can`t get all Books", e);
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return Optional.ofNullable(entityManager
                    .createQuery("SELECT b FROM Book b WHERE b.id = :id", Book.class)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (Exception e) {
            throw new RuntimeException("Cant get Book by id " + id, e);
        }
    }
}
