package com.qiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiao.pojo.Comments;
import com.qiao.service.CommentsService;
import com.qiao.mapper.CommentsMapper;
import org.springframework.stereotype.Service;

/**
* @author QiaoYu
* @description 针对表【comments】的数据库操作Service实现
* @createDate 2022-04-22 17:24:26
*/
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments>
    implements CommentsService{

}




