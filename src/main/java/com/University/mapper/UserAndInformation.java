package com.University.mapper;

import com.University.model.User_Information;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserAndInformation {
    @Select("select * from information_number where user_id = #{userId}")
    public List<User_Information> userAndInformation(@Param("userId") Integer userId);
}
