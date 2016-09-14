package com.sample.test.repository;

import com.sample.test.BaseTest;
import com.sample.test.user.domain.Author;
import com.sample.test.user.domain.Book;
import com.sample.test.user.repository.AuthorRepository;
import com.sample.test.user.repository.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 16-6-14.
 */
public class BookRepositoryTest extends BaseTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void save1() {
        Book book = new Book();
        book.setName("xiaoaojianghu");
        bookRepository.save(book);
    }
    @Test
    public void save2() {
        Book book = new Book();
        book.setName("xiaolifeidao");
        Author author = authorRepository.findOne(Long.valueOf(10000061));
        book.setAuthor(author);
        bookRepository.save(book);
    }

    @Test
    public void find1() {
        Book book = bookRepository.findOne(Long.valueOf(10000063));
        Assert.assertNotNull(book);
        Author author = book.getAuthor();
        Assert.assertNotNull(author);
    }
}
