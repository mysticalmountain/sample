package com.sample.configcenter.repository;

import com.sample.configcenter.model.Kei;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-1.
 */
@Component
public interface KeiRepository extends JpaRepository<Kei, Long> {

    public Kei findByKei(String kei);
}
