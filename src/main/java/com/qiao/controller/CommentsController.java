package com.qiao.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiao.pojo.Comments;
import com.qiao.pojo.Orders;
import com.qiao.pojo.Shoes;
import com.qiao.service.CommentsService;
import com.qiao.service.ShoesService;
import com.qiao.utils.JWTUtils;
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
 * @since 2022-04-04
 */
@RestController
@RequestMapping("/comment")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;
    @Autowired
    private ShoesService shoesService;

    // 新增或者更新
    @PostMapping("/save")
    public ResponseResult save(@RequestBody Comments comment) {
        if (comment.getCid() == null) { // 新增评论
            comment.setSctime(DateUtil.date());
        }
        commentsService.saveOrUpdate(comment);
        return ResponseResult.okResult();
    }

    @PostMapping ("/delete")
    public ResponseResult delete(@RequestParam Integer id) {
        QueryWrapper<Comments>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("cid",id);
        commentsService.removeById(id);
        return ResponseResult.okResult();
    }
    @PostMapping("/getShoes")
    public ResponseResult getShoes(@RequestParam Integer cid) {
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", cid).select("csid");
        Comments one = commentsService.getOne(queryWrapper);
        int sid = one.getCsid();
        QueryWrapper<Shoes> shoesQueryWrapper = new QueryWrapper<>();
        shoesQueryWrapper.eq("sid", sid);
        return ResponseResult.okResult(shoesService.getOne(shoesQueryWrapper));
    }
    @PostMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
        commentsService.removeByIds(ids);
        return ResponseResult.okResult();
    }

    @GetMapping
    public ResponseResult findAll() {
        return ResponseResult.okResult(commentsService.list());
    }

    @GetMapping("/getComment")
    public ResponseResult findAll(@RequestParam Integer uid) {
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cuid", uid);
        return ResponseResult.okResult(commentsService.list(queryWrapper));
    }

    @GetMapping("/{id}")
    public ResponseResult findOne(@PathVariable Integer id) {
        return ResponseResult.okResult(commentsService.getById(id));
    }

    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize) {
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return ResponseResult.okResult(commentsService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

