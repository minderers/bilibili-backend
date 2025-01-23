package top.dl.bilibili.backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.dl.bilibili.backend.common.cache.RedisCache;
import top.dl.bilibili.backend.common.cache.RedisKeys;
import top.dl.bilibili.backend.common.config.RedisConfig;
import top.dl.bilibili.backend.common.result.PageResult;
import top.dl.bilibili.backend.mapper.CommentMapper;
import top.dl.bilibili.backend.mapper.UserMapper;
import top.dl.bilibili.backend.model.entity.Comment;
import top.dl.bilibili.backend.model.entity.User;
import top.dl.bilibili.backend.model.query.CommentQuery;
import top.dl.bilibili.backend.model.query.UserCommentQuery;
import top.dl.bilibili.backend.model.vo.CommentVO;
import top.dl.bilibili.backend.service.CommentService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/17 20:40
 * @description:
 **/
@Slf4j
@Service
@AllArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Resource
    private UserMapper userMapper;

    @Override
    public PageResult<CommentVO> getCommentList(CommentQuery query) {
        Page<CommentVO> page = new Page<>(query.getPage(), query.getLimit());
        List<CommentVO> list = baseMapper.selectCommentList(page, query);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public CommentVO addComment(Integer userId, Integer videoId, String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUserId(userId);
        comment.setVideoId(videoId);
        baseMapper.insert(comment);
        User user = userMapper.selectById(userId);
        CommentVO commentVO = new CommentVO();
        commentVO.setPkId(comment.getPkId());
        commentVO.setNickname(user.getNickname());
        commentVO.setContent(content);
        commentVO.setCreateTime(LocalDateTime.now());
        return commentVO;
    }

    @Override
    public CommentVO deleteComment(Integer id) {
        Comment comment = baseMapper.selectById(id);
        baseMapper.deleteById(id);
        return null;
    }

    @Override
    public PageResult<CommentVO> getNewCommentList(UserCommentQuery query) {
        Page<CommentVO> page = new Page<>(query.getPage(), query.getLimit());
        List<CommentVO> list = baseMapper.selectNewCommentList(page, query);
        return new PageResult<>(list, page.getTotal());
    }
}

