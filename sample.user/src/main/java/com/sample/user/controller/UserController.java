//package com.sample.user.controller;
//
//import com.sample.user.repository.UserRepository;
//import com.sample.user.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.jca.cci.core.InteractionCallback;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * Created by andongxu on 16-6-6.
// */
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<User> list() {
//        List<User> users = userRepository.findAll();
//        System.out.println(users);
//        return users;
//    }
//
//    @RequestMapping(path = "{id}", method = RequestMethod.GET)
//    public User query(@PathVariable Long id) {
//        return userRepository.findOne(id);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public User save(User user) {
//        userRepository.save(user);
//        return user;
//    }
//
//    @RequestMapping(path = "{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public User update(@RequestParam("id") Long id, User user) {
//        return userRepository.save(user);
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE)
//    public void delete(@RequestParam("id") Long id) {
//        userRepository.delete(id);
//    }
//}
