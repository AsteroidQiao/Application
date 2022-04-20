package com.qiao.controller;

import ch.qos.logback.core.util.TimeUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiao.pojo.Orders;
import com.qiao.pojo.Receives;
import com.qiao.pojo.SecondKills;
import com.qiao.pojo.Shoes;
import com.qiao.service.OrdersService;
import com.qiao.service.ReceivesService;
import com.qiao.service.ShoesService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiao.config.ResponseResult;


import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 乔羽
 * @since 2022-04-16
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private ReceivesService receivesService;
    @Autowired
    private ShoesService shoesService;

    // 新增
    @PostMapping("/save")
    public ResponseResult save(@RequestParam Integer ouid, @RequestParam Integer sid, @RequestParam Integer ocount, @RequestParam Integer orecid, @RequestParam double ototal) {
        Orders orders = new Orders();
        orders.setOuid(ouid);
        orders.setSid(sid);
        orders.setOcount(ocount);
        orders.setOrecid(orecid);
        orders.setOtotal(ototal);
        //编号为当前时间
        orders.setOnum(String.valueOf(DateUtil.now().hashCode()));
        orders.setOrdertime(DateUtil.date());
        //0代表未处理
        orders.setOstate(0);
        ordersService.save(orders);
        return ResponseResult.okResult();
    }

    @PostMapping("/delete")
    public ResponseResult delete(@RequestParam Integer id) {
        ordersService.removeById(id);
        return ResponseResult.okResult();
    }

    @PostMapping("/confirm")
    public ResponseResult confirm(@RequestParam Integer oid, @RequestParam Integer ostate) {
        UpdateWrapper<Orders> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("oid", oid).set("ostate", ostate);
        ordersService.update(updateWrapper);
        return ResponseResult.okResult();
    }

    @PostMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
        ordersService.removeByIds(ids);
        return ResponseResult.okResult();
    }

    @PostMapping("/getAddress")
    public ResponseResult getAddress(@RequestParam Integer oid) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("oid", oid).select("orecid");
        Orders one = ordersService.getOne(queryWrapper);
        int orecid = one.getOrecid();
        QueryWrapper<Receives> receivesQueryWrapper = new QueryWrapper<>();
        receivesQueryWrapper.eq("recid", orecid);
        return ResponseResult.okResult(receivesService.getOne(receivesQueryWrapper));
    }

    @PostMapping("/getShoes")
    public ResponseResult getShoes(@RequestParam Integer oid) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("oid", oid).select("sid");
        Orders one = ordersService.getOne(queryWrapper);
        int sid = one.getSid();
        QueryWrapper<Shoes> shoesQueryWrapper = new QueryWrapper<>();
        shoesQueryWrapper.eq("sid", sid);
        return ResponseResult.okResult(shoesService.getOne(shoesQueryWrapper));
    }

    @GetMapping
    public ResponseResult findAll() {
        return ResponseResult.okResult(ordersService.list());
    }

    @GetMapping("/{id}")
    public ResponseResult findOne(@PathVariable Integer id) {
        return ResponseResult.okResult(ordersService.getById(id));
    }

    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam Integer ostate, @RequestParam Integer ouid) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        if (ostate == 10) {
            queryWrapper.orderByAsc("oid").eq("ouid",ouid);
        } else {
            queryWrapper.orderByAsc("oid").eq("ouid",ouid);
            queryWrapper.eq("ostate", ostate);
        }
        return ResponseResult.okResult(ordersService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

