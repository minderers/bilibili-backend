package top.dl.bilibili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import top.dl.bilibili.backend.common.exception.ErrorCode;
import top.dl.bilibili.backend.common.exception.ServerException;
import top.dl.bilibili.backend.mapper.CommentMapper;
import top.dl.bilibili.backend.mapper.ReplyMapper;
import top.dl.bilibili.backend.mapper.UserMapper;
import top.dl.bilibili.backend.model.entity.Comment;
import top.dl.bilibili.backend.model.entity.Reply;
import top.dl.bilibili.backend.model.entity.User;
import top.dl.bilibili.backend.model.vo.ReplyVO;
import top.dl.bilibili.backend.service.ReplyService;

import java.time.LocalDateTime;

/**
 * @author: minder
 * @createTime: 2025/01/18 19:09
 * @description:
 **/
@Slf4j
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private CommentMapper commentMapper;
    @Override
    public ReplyVO addReply(Integer commentId, Integer userId , String content) {
        Comment comment = commentMapper.selectById(commentId);
        if (ObjectUtils.isEmpty(comment)) {
            throw new ServerException(ErrorCode.OPERATION_FAIL);
        }
        Reply reply = new Reply();
        reply.setPkId(reply.getPkId());
        reply.setCommentId(commentId);
        reply.setContent(content);
        reply.setUserId(userId);
        baseMapper.insert(reply);
        User user = userMapper.selectById(userId);
        ReplyVO replyVO = new ReplyVO();
        replyVO.setPkId(reply.getPkId());
        replyVO.setContent(content);
        replyVO.setNickname(user.getNickname());
        replyVO.setCommentId(commentId);
        replyVO.setCreateTime(LocalDateTime.now());
        return replyVO;
    }
}
