package top.dl.bilibili.backend.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.dl.bilibili.backend.model.entity.Like;
import top.dl.bilibili.backend.model.entity.User_Star;
import top.dl.bilibili.backend.model.query.StarQuery;
import top.dl.bilibili.backend.model.vo.LikeVO;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/21 19:35
 * @description:
 **/
public interface LikeMapper extends BaseMapper<Like> {
    List<LikeVO> selectLikeByUserId(Page<LikeVO> page, @Param("query") StarQuery query);
    default Like selectLikeByUserIdAndVideoId(@Param("userId") Integer userId, @Param("videoId") Integer videoId) {
        QueryWrapper<Like> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("video_id", videoId);
        return selectOne(queryWrapper);
    }
}
