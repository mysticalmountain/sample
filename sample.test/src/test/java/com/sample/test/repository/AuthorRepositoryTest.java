package com.sample.test.repository;

import com.sample.test.BaseTest;
import com.sample.test.user.domain.Author;
import com.sample.test.user.domain.AuthorProfile;
import com.sample.test.user.domain.Book;
import com.sample.test.user.repository.AuthorProfileRepository;
import com.sample.test.user.repository.AuthorRepository;
import com.sample.test.user.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andongxu on 16-6-14.
 */
public class AuthorRepositoryTest extends BaseTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorProfileRepository authorProfileRepository;

    @Test
    public void save1() {
        Author author = new Author();
        author.setName("jinyong");
        authorRepository.save(author);
    }

    @Test
    public void save2() {
        Author author = new Author();
        author.setName("gulong");
        Book book = new Book();
        book.setName("xiaolifeidao");
        Set<Book> books = new HashSet<Book>();
        books.add(book);
        author.setBooks(books);
        authorRepository.save(author);
    }

    @Test
    public void save3() {
        AuthorProfile authorProfile = new AuthorProfile();
        authorProfile.setAddress("taiwan");
        authorProfile.setPhone("222222222");
        authorProfile.setZip("100000");
        authorProfileRepository.save(authorProfile);
    }

    @Test
    public void save4() {
        Author author = authorRepository.findOne(Long.valueOf(10000061));
        AuthorProfile authorProfile = authorProfileRepository.findOne(Long.valueOf(10000065));
        authorProfile.setAuthor(author);
        authorProfileRepository.save(authorProfile);
    }

    @Test
    public void save5() {
        Author author = new Author();
        author.setId(Long.valueOf(10000061));
        author.setName("金庸");
        AuthorProfile authorProfile = new AuthorProfile();
        authorProfile.setPhone("11111111111");
        authorProfile.setAddress("台湾");
        authorProfileRepository.save(authorProfile);
    }

    @Test
    public void save6() {
        AuthorProfile authorProfile = authorProfileRepository.findOne(Long.valueOf(10000070));
        authorProfile.setCity("taizhong");
        authorProfileRepository.save(authorProfile);
    }

    @Test
    public void save7() {
        for (int i = 0; i < 5; i++) {
            Author author = new Author();
            author.setName("name-" + i);
            authorRepository.save(author);
        }
    }
}
