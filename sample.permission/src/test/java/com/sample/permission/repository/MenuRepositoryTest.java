package com.sample.permission.repository;

import com.alibaba.fastjson.TypeReference;
import com.sample.permission.BaseTest;
import com.sample.permission.dto.MenuDto;
import com.sample.permission.model.Menu;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 16-11-24.
 */
public class MenuRepositoryTest extends BaseTest {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void save1() {
        Menu menu = new Menu();
        menu.setName("n0");
        menuRepository.save(menu);
        Assert.assertNotNull(menu.getId());
    }

    @Test
    public void save2() {
        Menu menu0 = menuRepository.findOne(Long.valueOf(10000000));
        Menu menu1 = new Menu();
        menu1.setName("n1");
        menu1.setParent(menu0);
        Menu menu2 = new Menu();
        menu2.setName("n2");
        menu2.setParent(menu0);
        Menu menu3 = new Menu();
        menu3.setName("n3");
        menu3.setParent(menu0);
        menuRepository.save(menu1);
        menuRepository.save(menu2);
        menuRepository.save(menu3);
        Assert.assertNotNull(menu2.getId());
    }

    @Test
    public void save3() {
        Menu menu1 = menuRepository.findOne(Long.valueOf(10000001));
        Menu menu11 = new Menu();
        menu11.setName("n11");
        menu11.setParent(menu1);
        Menu menu12 = new Menu();
        menu12.setName("n12");
        menu12.setParent(menu1);
        menuRepository.save(menu11);
        menuRepository.save(menu12);
    }

    MenuDto root = null;

    @Test
    public void query() {
        Menu menu0 = menuRepository.findOne(Long.valueOf(10000000));
        BeanUtils.copyProperties(menu0, root);
        queryTree(menu0);
    }

    public void queryTree(Menu menu) {
        System.out.println(menu.getName());
        Set<Menu> menuSet = menu.getSons();
        Iterator<Menu> menuIterator = menuSet.iterator();
        while ((menuIterator.hasNext())) {
            Menu sonMenu = menuIterator.next();
            MenuDto menuDto = new MenuDto();
            BeanUtils.copyProperties(sonMenu, menuDto);
            queryTree(sonMenu);
        }
    }

    @Test
    public void copyTree() {
        Menu menu0 = menuRepository.findOne(Long.valueOf(10000000));
        MenuDto menuDto = new MenuDto();
        menuDto.setName(menu0.getName());
        assembleTree(menu0, menuDto);
        Assert.assertNotNull(menu0);
    }

    public void assembleTree(Menu menu, MenuDto menuDto) {
        System.out.println(menu.getName());
        Set<Menu> menuSet = menu.getSons();
        Iterator<Menu> menuIterator = menuSet.iterator();
        while ((menuIterator.hasNext())) {
            Menu sonMenu = menuIterator.next();
            MenuDto sonMenuDto = new MenuDto();
            sonMenuDto.setName(sonMenu.getName());
            if (menuDto.getChildren() != null) {
                menuDto.getChildren().add(sonMenuDto);
//                sonMenuDto.setMenuParent(menuDto);
            } else {
                Set<MenuDto> sons = new HashSet<MenuDto>();
                sons.add(sonMenuDto);
                menuDto.setChildren(sons);
//                sonMenuDto.setMenuParent(menuDto);
            }
            assembleTree(sonMenu, sonMenuDto);
        }
    }

//    public void tree(Tree<T> menu, Tree menuDto) {
//        Set<> menuSet = menu.getSons();
//        Iterator<Menu> menuIterator = menuSet.iterator();
//        while ((menuIterator.hasNext())) {
//            Menu sonMenu = menuIterator.next();
//            Tree<MenuDto>
//        }
//    }

    @Test
    public void queryAndCopy() {
        Menu menu0 = menuRepository.findOne(Long.valueOf(10000000));
        MenuDto menuDto = new MenuDto();
        BeanUtils.copyProperties(menu0, menuDto);
        Assert.assertNotNull(menu0);
    }

    @Test
    public void testTree() {

        Menu menu0 = menuRepository.findOne(Long.valueOf(10000000));
        TreeUtil<Menu> treeUtil = new TreeUtil<Menu>();
        treeUtil.each(new Tree<Menu>());
    }


}
