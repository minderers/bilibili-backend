package top.dl.bilibili.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.dl.bilibili.backend.common.result.PageResult;
import top.dl.bilibili.backend.model.entity.Comment;
import top.dl.bilibili.backend.model.query.CommentQuery;
import top.dl.bilibili.backend.model.vo.CommentVO;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/17 20:29
 * @description:
 **/
public interface CommentMapper extends BaseMapper<Comment> {
    List<CommentVO> selectCommentList(Page<CommentVO> page, @Param("query") CommentQuery query);
}
