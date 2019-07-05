package com.by.mapper;

import com.by.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by ${zw} on 2019/7/4.
 */
@Mapper
public interface UserMapper {


    @Select("select * from t_user where user_name = #{username}")
    User findUserByUserName(@Param("username") String username);
}
