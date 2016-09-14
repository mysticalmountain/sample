package com.sample.permission.repository;

import com.sample.permission.model.Service;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Created by andongxu on 16-8-29.
 */
@Component
public interface ServiceRepository extends JpaRepository<Service, Long> {

    public Service findByCode(String code);

//    public Collection<ServiceDto> findAll(Example<ServiceDto> example, Pageable pageable);

}
