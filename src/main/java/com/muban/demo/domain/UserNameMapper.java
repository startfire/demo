package com.muban.demo.domain;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserNameMapper {

    @Select("SELECT * FROM USER WHERE NAME = #{name} ")
    UserName findByName(@Param("name") String name);

    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);


    @Update( "update USER AS rule1 JOIN USER AS rule2 ON (rule1.id = #{oldid} AND rule2.id = #{newid}) SET rule1.age  = rule2.age,rule2.age  = rule1.age,rule1.name= rule2.name,rule2.name = rule1.name,")
    boolean change(@Param("oldid") String oldid, @Param("newid") String newid);


    @Select("SELECT * FROM USER WHERE age<#{age} ")
    List<UserName> selectById(@Param("age") String age);



    @Select("SELECT * FROM USER WHERE age<#{age} order by age ")
    List<UserName> selectALLById(@Param("age") String age);

//    @Insert("INSERT INTO USER(code) VALUES(#{code})where name=#{name}")
//    int addCode(@Param("code")String code ,@Param("name" )String name);

    @Update( "update USER AS rule1 JOIN USER AS rule2 ON (rule1.id = #{oldid} AND rule2.id = #{newid}) SET rule1.age  = rule2.age,rule2.age  = rule1.age,rule1.name= rule2.name,rule2.name = rule1.name,")
    boolean changetest(@Param("oldid") String oldid, @Param("newid") String newid);

}