package io.phiysng.springmybatish2.mapper;

import io.phiysng.springmybatish2.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where id= #{id}")
    User findById(Integer id);
}
