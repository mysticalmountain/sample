package com.sample.permission.repository;

import com.sample.permission.BaseTest;
import com.sample.permission.model.Tmp;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 16-9-1.
 */
public class TmpRepositoryTest extends BaseTest {

    @Autowired
    private TmpRepository tmpRepository;

    @Test
    public void insert() {
        Tmp tmp = new Tmp();
        tmp.setName("中文");
        tmpRepository.save(tmp);
    }
}
