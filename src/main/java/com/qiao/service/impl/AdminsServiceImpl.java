package com.qiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiao.pojo.Admins;
import com.qiao.service.AdminsService;
import com.qiao.mapper.AdminsMapper;
import org.springframework.stereotype.Service;

/**
* @author QiaoYu
* @description 针对表【admins】的数据库操作Service实现
* @createDate 2022-03-22 18:56:10
*/
@Service
public class AdminsServiceImpl extends ServiceImpl<AdminsMapper, Admins>
    implements AdminsService{

}




