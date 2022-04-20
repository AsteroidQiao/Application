package com.qiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiao.pojo.Shoes;
import com.qiao.service.ShoesService;
import com.qiao.mapper.ShoesMapper;
import org.springframework.stereotype.Service;

/**
* @author QiaoYu
* @description 针对表【shoes】的数据库操作Service实现
* @createDate 2022-04-11 19:49:07
*/
@Service
public class ShoesServiceImpl extends ServiceImpl<ShoesMapper, Shoes>
    implements ShoesService{

}




