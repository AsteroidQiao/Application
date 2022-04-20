package com.qiao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiao.pojo.Shoppingcart;
import com.qiao.service.ShoppingcartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class mytest {
    @Autowired
    private ShoppingcartService shoppingcartService;

    @Test
    void shoes() {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Shoppingcart> ids = queryWrapper.orderByAsc("sid").eq("uid", 1).select("sid");
        shoppingcartService.getBaseMapper().selectList(queryWrapper);
        System.out.println(ids);
    }
}
