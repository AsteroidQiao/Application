package com.qiao.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiao.pojo.Types;
import com.qiao.service.TypesService;
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
@RequestMapping("/types")
public class TypesController {

    @Autowired
    private TypesService typesService;

    // 新增或者更新
    @PostMapping
    public ResponseResult save(@RequestBody Types types) {
        typesService.saveOrUpdate(types);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Integer id) {
        typesService.removeById(id);
        return ResponseResult.okResult();
    }

    @PostMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
        typesService.removeByIds(ids);
        return ResponseResult.okResult();
    }

    @GetMapping
    public ResponseResult findAll() {
        return ResponseResult.okResult(typesService.list());
    }

    @GetMapping("/{id}")
    public ResponseResult findOne(@PathVariable Integer id) {
        return ResponseResult.okResult(typesService.getById(id));
    }

    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<Types> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return ResponseResult.okResult(typesService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

