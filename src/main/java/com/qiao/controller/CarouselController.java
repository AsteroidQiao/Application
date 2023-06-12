package com.qiao.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiao.service.CarouselService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiao.pojo.Carousel;
import com.qiao.config.ResponseResult;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 乔羽
 * @since 2023-04-23
 */
@RestController
@RequestMapping("/carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    // 新增或者更新
    @PostMapping("/save")
    public ResponseResult save(@RequestBody Carousel carousel) {
        carouselService.saveOrUpdate(carousel);
        return ResponseResult.okResult();
    }

    // 新增或者更新
    @PostMapping("/addCarousel")
    public ResponseResult add(@RequestBody Carousel carousel) {
        carouselService.save(carousel);
        return ResponseResult.okResult(200, "添加成功");
    }
    // 新增或者更新
    @PostMapping("/add")
    public ResponseResult add(Integer id,String url,String route,String info) {
        Carousel carousel=new Carousel();
        carousel.setId(id);
        carousel.setUrl(url);
        carousel.setRoute(route);
        carousel.setInfo(info);
        carouselService.save(carousel);
        return ResponseResult.okResult(200, "添加成功");
    }

    //完全删除
    @DeleteMapping("/{id}")
    public ResponseResult deleteAdmin(@PathVariable Integer id) {
        carouselService.removeById(id);
        return ResponseResult.okResult();
    }

    //逻辑删除 (假删除)
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable Integer id) {
        UpdateWrapper<Carousel> UpdateWrapper = new UpdateWrapper<>();
        UpdateWrapper.set("isdelete", 1).eq("id", id);
        carouselService.update(UpdateWrapper);
        return ResponseResult.okResult();
    }

    //批量删除
    @PostMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
        carouselService.removeByIds(ids);
        return ResponseResult.okResult();
    }

    //查找所有的记录
    @GetMapping("/findAll")
    public ResponseResult findAllAdmin() {
        return ResponseResult.okResult(carouselService.list());
    }


    //通过id查询
    @GetMapping("/{id}")
    public ResponseResult findOne(@PathVariable Integer id) {
        return ResponseResult.okResult(carouselService.getById(id));
    }

    //分页展示
    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String name) {
        QueryWrapper<Carousel> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return ResponseResult.okResult(carouselService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

