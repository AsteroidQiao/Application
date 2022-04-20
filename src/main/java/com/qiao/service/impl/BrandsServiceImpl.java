package com.qiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiao.pojo.Brands;
import com.qiao.service.BrandsService;
import com.qiao.mapper.BrandsMapper;
import org.springframework.stereotype.Service;

/**
* @author QiaoYu
* @description 针对表【brands】的数据库操作Service实现
* @createDate 2022-04-15 15:49:57
*/
@Service
public class BrandsServiceImpl extends ServiceImpl<BrandsMapper, Brands>
    implements BrandsService{

}




