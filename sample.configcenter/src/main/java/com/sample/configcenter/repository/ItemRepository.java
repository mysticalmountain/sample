package com.sample.configcenter.repository;

import com.sample.configcenter.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-1.
 */
@Component
public interface ItemRepository extends JpaRepository<Item, Long> {
}
