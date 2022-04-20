package com.qiao.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiao.pojo.Receives;
import com.qiao.service.ReceivesService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Array;
import java.util.ArrayList;
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
 * @since 2022-04-15
 */
@RestController
@RequestMapping("/receives")
public class ReceivesController {

    @Autowired
    private ReceivesService receivesService;

    // 新增或者更新
    @PostMapping
    public ResponseResult save(@RequestParam Integer uid, @RequestBody Receives receives) {
        receives.setRuid(uid);
        receivesService.saveOrUpdate(receives);
        return ResponseResult.okResult();
    }

    // 默认地址
    @PostMapping("/moren")
    public ResponseResult moren(@RequestParam Integer uid, @RequestParam Integer recid) {
        //先把所有都设成非默认0
        UpdateWrapper<Receives> receivesUpdateWrapper = new UpdateWrapper<>();
        receivesUpdateWrapper.set("recisdefault", 0);
        receivesService.update(receivesUpdateWrapper);
        //把传来的recid设成默认1
        UpdateWrapper<Receives> receivesUpdateWrapperSet = new UpdateWrapper<>();
        receivesUpdateWrapper.set("recisdefault", 1).eq("recid", recid).eq("ruid", uid);
        boolean update = receivesService.update(receivesUpdateWrapperSet);
        if (update) {
            return ResponseResult.okResult(200, "设置成功");
        } else {
            return ResponseResult.okResult(201, "设置失败");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Integer id) {
        receivesService.removeById(id);
        return ResponseResult.okResult();
    }

    @PostMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
        receivesService.removeByIds(ids);
        return ResponseResult.okResult();
    }

    @GetMapping
    public ResponseResult findAll() {
        return ResponseResult.okResult(receivesService.list());
    }

    @GetMapping("/{uid}")
    public ResponseResult findList(@PathVariable Integer uid) {
        QueryWrapper<Receives> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ruid", uid);
        return ResponseResult.okResult(receivesService.list(queryWrapper));
    }

    @PostMapping("/findid")
    public ResponseResult findid(@RequestBody List<Integer> recids) {
        QueryWrapper<Receives> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("recid", recids);
        return ResponseResult.okResult(receivesService.list(queryWrapper));
    }

    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize) {
        QueryWrapper<Receives> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return ResponseResult.okResult(receivesService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

