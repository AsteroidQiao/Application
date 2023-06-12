package com.qiao.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiao.pojo.Files;
import com.qiao.service.impl.FilesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;


@RestController
@RequestMapping("/file")
public class FileController {
    //定义上传地址
//    123.60.85.87      华为云
//    127.0.0.1         本地
    private String fileUploadPath = "D:/load/";
    //    private String fileUploadPath = "/www/wwwroot/load/";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private FilesServiceImpl filesService;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        // 定义一个文件唯一的标识码
        String fileUUID = IdUtil.fastSimpleUUID() + StrUtil.DOT + type;

        File uploadFile = new File(fileUploadPath + fileUUID);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        String url;
        // 获取文件的md5(加密的唯一标识)
        String md5 = SecureUtil.md5(file.getInputStream());
        // 从数据库查询是否存在相同的记录
        Files dbFiles = getFileByMd5(md5);
        if (dbFiles != null) {
            // 数据库若存在重复文件，返回url
            url = dbFiles.getUrl();
        } else {
            // 数据库若不存在重复文件，上传文件到磁盘
            file.transferTo(uploadFile);
            // 生成url
//            url = "http://123.60.85.87:9090/file/" + fileUUID;
            url = "http://127.0.0.1:9090/file/" + fileUUID;
        }

        // 存储数据库，数据库允许有重复文件
        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size / 1024); // 单位 kb
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        filesService.getBaseMapper().insert(saveFile);

        // 更新之后清空缓存
        delCache("Test");

        return url;
    }

    /**
     * 文件下载接口
     *
     * @param fileUUID
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");
        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    /**
     * 通过文件的md5查询文件
     *
     * @param md5
     * @return
     */
    private Files getFileByMd5(String md5) {
        // 查询文件的md5是否存在
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<Files> filesList = filesService.getBaseMapper().selectList(queryWrapper);
        return filesList.size() == 0 ? null : filesList.get(0);
    }

    // 设置缓存
    private void setCache(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    // 删除缓存
    private void delCache(String key) {
        stringRedisTemplate.delete(key);
    }

}
