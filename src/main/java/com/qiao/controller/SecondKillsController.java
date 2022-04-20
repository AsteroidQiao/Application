package com.qiao.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiao.pojo.SecondKills;
import com.qiao.pojo.Shoes;
import com.qiao.service.SecondKillsService;
import com.qiao.service.ShoesService;
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
 * @since 2022-04-14
 */
@RestController
@RequestMapping("/secondkills")
public class SecondKillsController {

    @Autowired
    private SecondKillsService secondKillsService;
    @Autowired
    private ShoesService shoesService;

    // 新增或者更新
    @PostMapping
    public ResponseResult save(@RequestBody SecondKills secondKills) {
        secondKillsService.saveOrUpdate(secondKills);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Integer id) {
        secondKillsService.removeById(id);
        return ResponseResult.okResult();
    }

    @PostMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
        secondKillsService.removeByIds(ids);
        return ResponseResult.okResult();
    }

    @GetMapping
    public ResponseResult findAll() {
        return ResponseResult.okResult(secondKillsService.list());
    }

    @GetMapping("/{id}")
    public ResponseResult findOne(@PathVariable Integer id) {
        return ResponseResult.okResult(secondKillsService.getById(id));
    }

    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam String name,
                                   @RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize) {
        //查询秒杀商品的sid，
        QueryWrapper<SecondKills> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("skid");
        List<SecondKills> secondKills = secondKillsService.getBaseMapper().selectList(queryWrapper);
        ArrayList<Integer> ids = new ArrayList<>();
        for(SecondKills item:secondKills){
            ids.add(item.getSkSid());
        }
        //shoes 从shoes表显示用户购物车列表 是sanme查询
        QueryWrapper<Shoes> queryWrapperKillShoes = new QueryWrapper<>();
        queryWrapperKillShoes.orderByAsc("sid").in("sid", ids);
        if (StrUtil.isNotBlank(name)) {
            queryWrapperKillShoes.like("sname", name);
        }
        return ResponseResult.okResult(shoesService.page(new Page<>(pageNum, pageSize), queryWrapperKillShoes),secondKills);
    }

}

