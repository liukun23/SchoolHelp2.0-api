package com.University.mapper;

import com.University.model.User;
import com.University.model.UserCode;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where openId=#{openId}")
    public UserCode SelectByOpenId(@Param("openId") String openId);
    @Insert("insert into user(user_name,nickname,avatarurl,sex,city,phone,integration,role_id,hobby_help_id,openId,unionId) values(#{user.userName},#{user.nickname},#{user.avatarurl},#{user.sex},#{user.city},#{user.phone},#{user.integration},#{user.roleId},#{user.hobbyHelpId},#{user.openId},#{user.unionId})")
    void addUser(@Param("user") UserCode user);
    @Select("select * from user where user_id=#{userId}")
    public User SelectByUserId(@Param("userId") Integer userId);
}
