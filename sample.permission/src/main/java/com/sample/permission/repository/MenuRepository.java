package com.sample.permission.repository;

import com.sample.permission.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andongxu on 16-8-26.
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
