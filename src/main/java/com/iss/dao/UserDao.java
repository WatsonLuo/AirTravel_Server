package com.iss.dao;

import com.iss.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    @Select("select * from users")
    public List<User> getAllUser();

    @Select("select * from users where email=#{flag} or phone=#{flag} or id=#{flag}")
    public User getTheUser(@Param("flag") String flag);

    @Insert("insert into users values(#{id},#{name},#{password},#{email},#{sex},#{phone},#{isOnline})")
    public int addUser(User user);

    @Update("update users set name=#{name},password=#{password},email=#{email},sex=#{sex},phone=#{phone},isonline=#{isOnline} where id=#{id}")
    public int updateUser(User user);

    @Delete("delete from users where email=#{flag} or phone=#{flag}")
    public int deleteUser(@Param("flag") String flag);

}
