package com.qiao.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiao.pojo.Comments;
import com.qiao.pojo.SpcifyShoes;
import com.qiao.service.SpcifyShoesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

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
 * @since 2022-04-15
 */
@RestController
@RequestMapping("/spcify-shoes")
public class SpcifyShoesController {

    @Autowired
    private SpcifyShoesService spcifyShoesService;

    // 新增或者更新
    @PostMapping
    public ResponseResult save(@RequestBody SpcifyShoes spcifyShoes) {
        spcifyShoesService.saveOrUpdate(spcifyShoes);
        return ResponseResult.okResult(200, "提交成功");
    }

    @PostMapping("/delete")
    public ResponseResult delete(@RequestParam Integer id) {
        QueryWrapper<SpcifyShoes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("spsid", id);
        spcifyShoesService.removeById(id);
        return ResponseResult.okResult();
    }

    @PostMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
        spcifyShoesService.removeByIds(ids);
        return ResponseResult.okResult();
    }

    @GetMapping
    public ResponseResult findAll() {
        return ResponseResult.okResult(spcifyShoesService.list());
    }

    @GetMapping("/{id}")
    public ResponseResult findOne(@PathVariable Integer id) {
        return ResponseResult.okResult(spcifyShoesService.getById(id));
    }

    @GetMapping("/getMy")
    public ResponseResult getMy(@RequestParam Integer uid) {
        QueryWrapper<SpcifyShoes> shoesQueryWrapper = new QueryWrapper<>();
        shoesQueryWrapper.eq("uid", uid);
        List<SpcifyShoes> list = spcifyShoesService.list(shoesQueryWrapper);
        return ResponseResult.okResult(list);
    }

    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize) {
        QueryWrapper<SpcifyShoes> queryWrapper = new QueryWrapper<>();
        //查询未过期秒杀商品
        queryWrapper.orderByDesc("id").eq("skisvalid", 0);
        return ResponseResult.okResult(spcifyShoesService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

