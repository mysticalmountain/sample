package com.sample.test.user.repository;

import com.sample.test.user.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andongxu on 16-6-14.
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
