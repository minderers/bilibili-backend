package top.dl.bilibili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.dl.bilibili.backend.common.result.PageResult;
import top.dl.bilibili.backend.model.entity.Video;
import top.dl.bilibili.backend.model.query.Query;
import top.dl.bilibili.backend.model.query.VideoQuery;
import top.dl.bilibili.backend.model.vo.VideoDetailVO;
import top.dl.bilibili.backend.model.vo.VideoVO;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/17 19:08
 * @description:
 **/
public interface VideoService extends IService<Video> {
    PageResult<VideoVO> getVideoList(Query query);
    VideoDetailVO detail(Integer id);
    PageResult<VideoVO> getCategoryVideoList(VideoQuery query);
    VideoVO delete(Integer id);
}
