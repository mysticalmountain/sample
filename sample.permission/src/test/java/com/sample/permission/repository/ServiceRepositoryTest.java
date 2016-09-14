package com.sample.permission.repository;

import com.sample.permission.BaseTest;
import com.sample.permission.dto.*;
import com.sample.permission.model.*;
import com.sample.permission.model.Service;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

/**
 * Created by andongxu on 16-8-30.
 */
@Transactional
@Commit
public class ServiceRepositoryTest extends BaseTest {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ChannelRepository channelRepository;

    @Test
    public void insert0() {
        Service service = new Service();
        service.setCode("1006");
        serviceRepository.save(service);
    }


    @Test
    public void findByCode0() {
        Service service = serviceRepository.findByCode("WY");
        Assert.assertNotNull(service);
        Assert.assertEquals("WY", service.getCode());
    }

    @Test
    public void findByCode1() {
        Service service = serviceRepository.findByCode("WY");
        Assert.assertNotNull(service);
        Set<Permission> permissions = service.getPermissions();
        Assert.assertNotNull(permissions);
        Assert.assertTrue(permissions.size() >= 1);
    }

    @Test
    public void findExample1() {
        Service service = new Service();
        service.setCode("100");
//        service.setId(Long.valueOf(1));
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("code", startsWith());
        Example<Service> example = Example.of(service, matcher);
//        Example<ServiceDto> example = Example.of(service);
        List<Service> serviceList = serviceRepository.findAll(example);
        Assert.assertNotNull(serviceList);
    }

    @Autowired
    private EntityManager entityManager;

    @Test
    public void findPageable() {
        String sql = "select id, code from Service";
        Query query = entityManager.createQuery(sql);
        query.setMaxResults(3);
        List res = query.getResultList();
        Assert.assertNotNull(res);

    }

    @Test
    public void findPageable2() {
        Service service = new Service();
        Example<Service> example = Example.of(service);
        Page<Service> services = serviceRepository.findAll(example, new Pageable() {
            @Override
            public int getPageNumber() {
                return 1;
            }

            @Override
            public int getPageSize() {
                return 3;
            }

            @Override
            public int getOffset() {
                return getPageNumber() * getPageSize();
            }

            @Override
            public Sort getSort() {
                new Sort(new Sort.Order[]{new Sort.Order(Sort.Direction.ASC, "code")});
                return null;
            }

            @Override
            public Pageable next() {
                return new Pageable() {
                    @Override
                    public int getPageNumber() {
                        return getPageNumber() + 1;
                    }

                    @Override
                    public int getPageSize() {
                        return 0;
                    }

                    @Override
                    public int getOffset() {
                        return 0;
                    }

                    @Override
                    public Sort getSort() {
                        return null;
                    }

                    @Override
                    public Pageable next() {
                        return null;
                    }

                    @Override
                    public Pageable previousOrFirst() {
                        return null;
                    }

                    @Override
                    public Pageable first() {
                        return null;
                    }

                    @Override
                    public boolean hasPrevious() {
                        return false;
                    }
                };
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        });
        Assert.assertNotNull(services);
        System.out.println("-------------------------------------------------------------------------");
        for (Service s1 : services) {
            System.out.println(s1.getCode());
        }
        System.out.println("-------------------------------------------------------------------------");
    }

}
