package com.qiao.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiao.pojo.Shoes;
import com.qiao.pojo.Shoppingcart;
import com.qiao.service.ShoesService;
import com.qiao.service.ShoppingcartService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiao.config.ResponseResult;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 乔羽
 * @since 2022-04-13
 */
@RestController
@RequestMapping("/shoppingcart")
public class ShoppingcartController {

    @Autowired
    private ShoppingcartService shoppingcartService;
    @Autowired
    private ShoesService shoesService;

    // 更新 scount
    @PostMapping("/update")
    public ResponseResult update(@RequestParam Integer uid, @RequestParam Integer sid, @RequestParam Integer scount) {
        UpdateWrapper<Shoppingcart> shoppingcartQueryWrapper = new UpdateWrapper<>();

        shoppingcartQueryWrapper.set("scount", scount).eq("uid", uid).eq("sid", sid);
        shoppingcartService.update(shoppingcartQueryWrapper);
        return ResponseResult.okResult(201, "成功");
    }

    //新增
    @PostMapping("/insert")
    public ResponseResult insert(@RequestParam Integer uid, @RequestParam Integer sid, @RequestParam Integer scount) {
//        UpdateWrapper<Shoppingcart> shoppingcartUpdateWrapper = new UpdateWrapper<>();
//
//        shoppingcartUpdateWrapper.eq("sid",sid).eq("sid",sid);
        Shoppingcart shoppingcart = new Shoppingcart();
        shoppingcart.setUid(uid);
        shoppingcart.setSid(sid);
        shoppingcart.setScount(scount);
        QueryWrapper<Shoppingcart> shoppingcartQueryWrapper = new QueryWrapper<>();
        shoppingcartQueryWrapper.eq("uid", uid).eq("sid", sid);
        Shoppingcart one = shoppingcartService.getOne(shoppingcartQueryWrapper);
        //如果数据库不存在记录则新增
        if (one == null) {
            shoppingcartService.save(shoppingcart);
        } else {//如果数据库不存在将scount加上新加入购物车的scount
            UpdateWrapper<Shoppingcart> shoppingcartUpdateWrapper = new UpdateWrapper<>();
            shoppingcartUpdateWrapper.eq("sid", sid).eq("sid", sid).set("scount", one.getScount() + shoppingcart.getScount());
            shoppingcartService.update(one, shoppingcartUpdateWrapper);
        }
        return ResponseResult.okResult(200, "添加成功");
    }

    @GetMapping("/delete")
    public ResponseResult delete(@RequestParam Integer uid, @RequestParam Integer sid) {
        QueryWrapper<Shoppingcart> shoppingcartQueryWrapper = new QueryWrapper<>();
        shoppingcartQueryWrapper.eq("uid", uid).eq("sid", sid);
        int delete = shoppingcartService.getBaseMapper().delete(shoppingcartQueryWrapper);
        if (delete != 0) {
            return ResponseResult.okResult(200, "删除成功");
        } else {
            return ResponseResult.okResult(201, "删除失败");
        }
    }

    @PostMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
        shoppingcartService.removeByIds(ids);
        return ResponseResult.okResult();
    }

    @GetMapping
    public ResponseResult findAll() {
        return ResponseResult.okResult(shoppingcartService.list());
    }

    @GetMapping("/{id}")
    public ResponseResult findOne(@PathVariable Integer id) {
        return ResponseResult.okResult(shoppingcartService.getById(id));
    }

    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam String name,
                                   @RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize, @RequestParam Integer uid) {
        //shoppingCart 查询用户已有的购物车列表
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sid").eq("uid", uid).select("sid", "scount", "size");
        List<Shoppingcart> Shoppingcart = shoppingcartService.getBaseMapper().selectList(queryWrapper);
        System.out.println("ddsaffafasfa===================================================" + Shoppingcart);
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<Integer> scounts = new ArrayList<>();
        ArrayList<Double> sizes = new ArrayList<>();
        //判断购物车是否为空
        if (Shoppingcart.isEmpty()) {
            return ResponseResult.okResult(201, "暂无数据");
        } else {
            for (Shoppingcart item : Shoppingcart) {
                ids.add(item.getSid());
                scounts.add(item.getScount());
                sizes.add(item.getSize());
            }

           List<Object>data2=new ArrayList<>();
            data2.add(scounts);
            data2.add(sizes);
            //shoes 从shoes表显示用户购物车列表 是sanme查询
            QueryWrapper<Shoes> queryWrapperShoes = new QueryWrapper<>();
            queryWrapperShoes.orderByAsc("sid").in("sid", ids);
            if (StrUtil.isNotBlank(name)) {
                queryWrapperShoes.like("sname", name);
            }
            return ResponseResult.okResult(shoesService.page(new Page<>(pageNum, pageSize), queryWrapperShoes), data2);
        }

    }

}

