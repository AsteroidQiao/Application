package com.qiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiao.pojo.Types;
import com.qiao.service.TypesService;
import com.qiao.mapper.TypesMapper;
import org.springframework.stereotype.Service;

/**
* @author QiaoYu
* @description 针对表【types】的数据库操作Service实现
* @createDate 2022-04-15 15:20:19
*/
@Service
public class TypesServiceImpl extends ServiceImpl<TypesMapper, Types>
    implements TypesService{

}




