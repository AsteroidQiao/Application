package com.qiao.controller;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiao.pojo.Shoes;
import com.qiao.service.ShoesService;
import org.springframework.data.redis.core.StringRedisTemplate;
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
@RequestMapping("/shoes")
public class ShoesController {

    @Autowired
    private ShoesService shoesService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 新增或者更新
    @PostMapping
    public ResponseResult save(@RequestBody Shoes shoes) {
        //新增或者更新刷新缓存
        flushRedis("page");
        shoesService.saveOrUpdate(shoes);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Integer id) {
        //删除刷新缓存
        flushRedis("page");
        shoesService.removeById(id);
        return ResponseResult.okResult();
    }

    @PostMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
        //删除刷新缓存
        flushRedis("page");
        shoesService.removeByIds(ids);
        return ResponseResult.okResult();
    }

    @GetMapping
    public ResponseResult findAll() {
        return ResponseResult.okResult(shoesService.list());
    }

    @GetMapping("/{id}")
    public ResponseResult findOne(@PathVariable Integer id) {
        return ResponseResult.okResult(shoesService.getById(id));
    }

    // 设置缓存 部署到云没有redis 就不用
    private Page<Shoes> Cache(String name,Integer pageNum, Integer pageSize) {
        // 1. 从缓存获取数据
        String jsonStr = stringRedisTemplate.opsForValue().get("page");
        Page<Shoes> page;
        if (StrUtil.isBlank(jsonStr)) {  // 2. 取出来的json是空的
            QueryWrapper<Shoes> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("sid");
            if (StrUtil.isNotBlank(name)) {
                queryWrapper.like("sname", name);
            }
            page = shoesService.page(new Page<>(pageNum, pageSize), queryWrapper);// 3. 从数据库取出数据
            // 4. 再去缓存到redis
            stringRedisTemplate.opsForValue().set("page", JSONUtil.toJsonStr(page));
        } else {
            // 减轻数据库的压力
            // 5. 如果有, 从redis缓存中获取数据
            page = JSONUtil.toBean(jsonStr, new TypeReference<Page<Shoes>>() {
            }, true);
        }
        return page;
    }

    // 删除缓存
    private void flushRedis(String key) {
        stringRedisTemplate.delete(key);
    }

    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam String name,
                                   @RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize) {

        //使用redis

        return ResponseResult.okResult(Cache(name,pageNum,pageSize));
        //没有使用redis
        //QueryWrapper<Shoes> queryWrapper = new QueryWrapper<>();
        //queryWrapper.orderByAsc("sid");
        //queryWrapper.like("sname", name);
        //Page<Shoes> page =shoesService.page(new Page<>(pageNum, pageSize), queryWrapper);
        //return ResponseResult.okResult(page);
    }
}

