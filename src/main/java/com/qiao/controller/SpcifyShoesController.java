package com.qiao.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 *  前端控制器
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
        return ResponseResult.okResult(200,"提交成功");
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Integer id) {
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

    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<SpcifyShoes> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return ResponseResult.okResult(spcifyShoesService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

