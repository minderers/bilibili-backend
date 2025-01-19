package top.dl.bilibili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.dl.bilibili.backend.common.result.PageResult;
import top.dl.bilibili.backend.model.entity.Comment;
import top.dl.bilibili.backend.model.query.CommentQuery;
import top.dl.bilibili.backend.model.vo.CommentVO;

/**
 * @author: minder
 * @createTime: 2025/01/17 20:39
 * @description:
 **/
public interface CommentService extends IService<Comment> {
    PageResult<CommentVO> getCommentList(CommentQuery query);
    CommentVO addComment(Integer userId, Integer videoId, String content);
}
