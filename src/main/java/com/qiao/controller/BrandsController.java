package com.qiao.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiao.pojo.Brands;
import com.qiao.service.BrandsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiao.config.ResponseResult;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 乔羽
 * @since 2022-04-15
 */
@RestController
@RequestMapping("/brands")
public class BrandsController {

    @Autowired
    private BrandsService brandsService;

    // 新增或者更新
    @PostMapping
    public ResponseResult save(@RequestBody Brands brands) {
        brandsService.saveOrUpdate(brands);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Integer id) {
        brandsService.removeById(id);
        return ResponseResult.okResult();
    }

    @PostMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
        brandsService.removeByIds(ids);
        return ResponseResult.okResult();
    }

    @GetMapping
    public ResponseResult findAll() {
        return ResponseResult.okResult(brandsService.list());
    }

    @GetMapping("/{id}")
    public ResponseResult findOne(@PathVariable Integer id) {
        return ResponseResult.okResult(brandsService.getById(id));
    }

    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<Brands> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return ResponseResult.okResult(brandsService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

