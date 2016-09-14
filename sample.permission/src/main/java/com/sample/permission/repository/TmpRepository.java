package com.sample.permission.repository;

import com.sample.permission.model.Tmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andongxu on 16-9-1.
 */
public interface TmpRepository extends JpaRepository<Tmp, Long> {


}
