package top.dl.bilibili.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.dl.bilibili.backend.model.entity.Video;
import top.dl.bilibili.backend.model.query.Query;
import top.dl.bilibili.backend.model.query.VideoQuery;
import top.dl.bilibili.backend.model.vo.VideoDetailVO;
import top.dl.bilibili.backend.model.vo.VideoVO;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/17 19:15
 * @description:
 **/
public interface VideoMapper extends BaseMapper<Video> {
    List<VideoVO> selectVideos(Page<VideoVO> page, @Param("query") Query query);
    VideoDetailVO getDetail(Integer id);
    List<VideoVO> selectCategoryVideos(Page<VideoVO> page, @Param("query") VideoQuery query);
}
