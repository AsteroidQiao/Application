package com.qiao.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.qiao.pojo.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author QiaoYu
* @description 针对表【users】的数据库操作Mapper
* @createDate 2022-03-23 15:43:01
* @Entity com.qiao.pojo.Users
*/
public interface UsersMapper extends BaseMapper<Users> {
    Users selectOneByUaccountAndUpwd(@Param("uaccount") String uaccount, @Param("upwd") String upwd);

    int insertAll(Users users);
    List<Users> getAllUser();

    Users selectByUaccount(@Param("uaccount") String uaccount);
}




