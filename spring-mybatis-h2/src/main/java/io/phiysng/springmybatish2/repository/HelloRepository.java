package io.phiysng.springmybatish2.repository;

import io.phiysng.springmybatish2.entity.User;
import io.phiysng.springmybatish2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository {
    @Autowired
    UserMapper mapper;

    public User getUserById(int id){
        return mapper.findById(id);
    }

}
