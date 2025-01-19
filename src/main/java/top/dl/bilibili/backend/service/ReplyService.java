package top.dl.bilibili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.dl.bilibili.backend.model.entity.Reply;
import top.dl.bilibili.backend.model.vo.ReplyVO;

/**
 * @author: minder
 * @createTime: 2025/01/18 19:09
 * @description:
 **/
public interface ReplyService extends IService<Reply> {
    ReplyVO addReply(Integer commentId,Integer userId,String content);
}
