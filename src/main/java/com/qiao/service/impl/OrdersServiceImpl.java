package com.qiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiao.pojo.Orders;
import com.qiao.service.OrdersService;
import com.qiao.mapper.OrdersMapper;
import org.springframework.stereotype.Service;

/**
* @author QiaoYu
* @description 针对表【orders】的数据库操作Service实现
* @createDate 2022-04-20 10:57:48
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

}




