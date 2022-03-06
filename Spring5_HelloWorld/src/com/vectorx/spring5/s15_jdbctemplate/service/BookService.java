package com.vectorx.spring5.s15_jdbctemplate.service;

import com.vectorx.spring5.s15_jdbctemplate.dao.BookDao;
import com.vectorx.spring5.s15_jdbctemplate.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public int addBook(Book book) {
        return bookDao.add(book);
    }
}
