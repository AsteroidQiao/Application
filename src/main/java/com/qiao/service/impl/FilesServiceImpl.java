package com.qiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiao.pojo.Files;
import com.qiao.service.FilesService;
import com.qiao.mapper.FilesMapper;
import org.springframework.stereotype.Service;

/**
* @author QiaoYu
* @description 针对表【files】的数据库操作Service实现
* @createDate 2022-03-31 09:22:14
*/
@Service
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files>
    implements FilesService{

}




