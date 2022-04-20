package com.qiao.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiao.pojo.Sizes;
import com.qiao.service.SizesService;
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
@RequestMapping("/sizes")
public class SizesController {

    @Autowired
    private SizesService sizesService;

    // 新增或者更新
    @PostMapping
    public ResponseResult save(@RequestBody Sizes sizes) {
        sizesService.saveOrUpdate(sizes);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Integer id) {
        sizesService.removeById(id);
        return ResponseResult.okResult();
    }

    @PostMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
        sizesService.removeByIds(ids);
        return ResponseResult.okResult();
    }

    @GetMapping
    public ResponseResult findAll() {
        return ResponseResult.okResult(sizesService.list());
    }

    @GetMapping("/{id}")
    public ResponseResult findOne(@PathVariable Integer id) {
        return ResponseResult.okResult(sizesService.getById(id));
    }

    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<Sizes> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return ResponseResult.okResult(sizesService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

