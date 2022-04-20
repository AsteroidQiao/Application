package com.qiao.mapper;
import org.apache.ibatis.annotations.Param;

import com.qiao.pojo.Admins;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author QiaoYu
* @description 针对表【admins】的数据库操作Mapper
* @createDate 2022-03-22 18:56:10
* @Entity com.qiao.pojo.Admins
*/
public interface AdminsMapper extends BaseMapper<Admins> {
    Admins selectOneByAcountAndApwd(@Param("acount") String acount, @Param("apwd") String apwd);

    List<Admins> getAllAdmin();

    int insertAll(Admins admins);
}




