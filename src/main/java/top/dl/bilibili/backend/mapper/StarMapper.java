package top.dl.bilibili.backend.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.dl.bilibili.backend.model.entity.User_Star;
import top.dl.bilibili.backend.model.query.Query;
import top.dl.bilibili.backend.model.query.StarQuery;
import top.dl.bilibili.backend.model.vo.StarVO;
import top.dl.bilibili.backend.model.vo.VideoVO;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/21 15:49
 * @description:
 **/
public interface StarMapper extends BaseMapper<User_Star> {
    List<StarVO> selectStarByUserId(Page<StarVO> page, @Param("query") StarQuery query);
    default User_Star selectStarByUserIdAndVideoId(@Param("userId") Integer userId, @Param("videoId") Integer videoId) {
        QueryWrapper<User_Star> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("video_id", videoId);
        return selectOne(queryWrapper);
    }
}
