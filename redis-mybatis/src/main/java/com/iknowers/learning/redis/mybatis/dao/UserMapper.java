package com.iknowers.learning.redis.mybatis.dao;

import com.iknowers.learning.redis.mybatis.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("Select * from user where id = #{id}")
    User selectById(@Param("id") Long id);

    /**
     * 插入并更新User#id
     * @param user
     */
    @Insert("insert into user(name, age, city) values(#{name}, #{age}, #{city})")
    @Options(useGeneratedKeys=true)
    void insert(User user);

    @Select("select * from user")
    List<User> selectAll();

    @Update("update user set name = #{name}, age = #{age}, city = #{city} where id = #{id}")
    void update(User user);

    @Delete("delete from user where id = #{id}")
    void deleteById(Long id);
}
