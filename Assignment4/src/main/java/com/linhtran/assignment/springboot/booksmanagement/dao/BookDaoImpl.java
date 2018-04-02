package com.linhtran.assignment.springboot.booksmanagement.dao;

import com.linhtran.assignment.springboot.booksmanagement.model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Book> list() {
        Query query = entityManager.createNativeQuery("select * from books");
        return (List<Book>) query.getResultList();
    }

    @Override
    @Transactional
    public Book findBookByID(int id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        entityManager.merge(book);
    }

    @Override
    @Transactional
    public void deleteBook(Book book) {
        entityManager.remove(book);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Book> searchBookByTitle(String title) {
        title = title.toLowerCase();
        Query query = entityManager
                .createQuery("select b from Book b where b.title like :searchValue")
                .setParameter("searchValue", "%"+title+"%");
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Book> searchBookByAuthor(String author) {
        author = author.toLowerCase();
        Query query = entityManager
                .createQuery("select b from from Book b where b.author like :searchValue")
                .setParameter("searchValue", "%"+author+"%");
        return query.getResultList();
    }
}
