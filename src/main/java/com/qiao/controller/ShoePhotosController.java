package com.qiao.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiao.pojo.ShoePhotos;
import com.qiao.service.ShoePhotosService;
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
 * @since 2022-04-11
 */
@RestController
@RequestMapping("/shoe-photos")
public class ShoePhotosController {

    @Autowired
    private ShoePhotosService shoePhotosService;

    // 新增或者更新
    @PostMapping
    public ResponseResult save(@RequestBody ShoePhotos shoePhotos) {
        shoePhotosService.saveOrUpdate(shoePhotos);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{sid}")
    public ResponseResult delete(@PathVariable Integer sid) {
        shoePhotosService.removeById(sid);
        return ResponseResult.okResult();
    }

    @PostMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
        shoePhotosService.removeByIds(ids);
        return ResponseResult.okResult();
    }

    @GetMapping
    public ResponseResult findAll() {
        return ResponseResult.okResult(shoePhotosService.list());
    }

    @GetMapping("/{sid}")
    public ResponseResult findOne(@PathVariable Integer sid) {
        return ResponseResult.okResult(shoePhotosService.getById(sid));
    }

    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam String name,
                                   @RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize) {
        QueryWrapper<ShoePhotos> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sid");
        if (StrUtil.isNotBlank(name)) {
            queryWrapper.like("sname", name);
        }
        return ResponseResult.okResult(shoePhotosService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

