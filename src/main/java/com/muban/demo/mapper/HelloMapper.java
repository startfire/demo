package com.muban.demo.mapper;

import com.muban.demo.domain.UserName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface HelloMapper {
    UserName getByName(String name);

    int addNumber(@Param("number") int number ,@Param("id") long id);

    int addUserName(@Param("id") long id,@Param("name")String name,@Param("age") int age);

    int add(@Param("name")String name,@Param("age") int age);


    int updateUserCode(@Param("code")String code,@Param("name") String name);
    int updateUser(UserName userName);
}
