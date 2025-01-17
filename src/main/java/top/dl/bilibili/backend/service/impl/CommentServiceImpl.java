package top.dl.bilibili.backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.dl.bilibili.backend.common.result.PageResult;
import top.dl.bilibili.backend.mapper.CommentMapper;
import top.dl.bilibili.backend.model.entity.Comment;
import top.dl.bilibili.backend.model.query.CommentQuery;
import top.dl.bilibili.backend.model.vo.CommentVO;
import top.dl.bilibili.backend.service.CommentService;

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
    @Override
    public PageResult<CommentVO> getCommentList(CommentQuery query) {
        Page<CommentVO> page = new Page<>(query.getPage(), query.getLimit());
        List<CommentVO> list = baseMapper.selectCommentList(page, query);
        return new PageResult<>(list, page.getTotal());
    }
}

