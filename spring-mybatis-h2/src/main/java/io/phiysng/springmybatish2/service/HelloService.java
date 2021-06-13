package io.phiysng.springmybatish2.service;

import io.phiysng.springmybatish2.entity.User;
import io.phiysng.springmybatish2.repository.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    @Autowired
    HelloRepository helloRepository;

    public User getUserById(int id) {
        return helloRepository.getUserById(id);
    }
}
