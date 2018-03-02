package com.linhtran.assignment.booksmanagement.dao;

import com.linhtran.assignment.booksmanagement.model.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Book> list() {
        TypedQuery<Book> query = sessionFactory.getCurrentSession().createQuery("from Book");
        return query.getResultList();
    }

    @Override
    @Transactional
    public Book findBookByID(int id) {
        return sessionFactory.getCurrentSession().get(Book.class, id);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        sessionFactory.getCurrentSession().update(book);
    }

    @Override
    @Transactional
    public void deleteBook(Book book) {
        sessionFactory.getCurrentSession().delete(book);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Book> searchBookByTitle(String title) {
        title = title.toLowerCase();
        TypedQuery<Book> query = sessionFactory
                .getCurrentSession()
                .createQuery("from Book where title like :searchValue")
                .setParameter("searchValue", "%"+title+"%");
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Book> searchBookByAuthor(String author) {
        author = author.toLowerCase();
        TypedQuery<Book> query = sessionFactory
                .getCurrentSession()
                .createQuery("from Book where author like :searchValue")
                .setParameter("searchValue", "%"+author+"%");
        return query.getResultList();
    }

}
