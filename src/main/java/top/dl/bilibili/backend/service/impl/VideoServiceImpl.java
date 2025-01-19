package top.dl.bilibili.backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.dl.bilibili.backend.common.cache.RedisCache;
import top.dl.bilibili.backend.common.cache.RedisKeys;
import top.dl.bilibili.backend.common.result.PageResult;
import top.dl.bilibili.backend.mapper.VideoMapper;
import top.dl.bilibili.backend.model.entity.Video;
import top.dl.bilibili.backend.model.query.Query;
import top.dl.bilibili.backend.model.vo.VideoDetailVO;
import top.dl.bilibili.backend.model.vo.VideoVO;
import top.dl.bilibili.backend.service.VideoService;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/17 19:22
 * @description:
 **/
@Service
@Slf4j
@AllArgsConstructor
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
    @Resource
    private RedisCache redisCache;
    @Override
    public PageResult<VideoVO> getVideoList(Query query) {
        Page<VideoVO> page = new Page<>(query.getPage(), query.getLimit());
        List<VideoVO> list = baseMapper.selectVideos(page,query);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public VideoDetailVO detail(Integer id) {
        // 将id存到Redis中
        String key = RedisKeys.getVideoIdKey(id);
        redisCache.set(key, id, RedisCache.DEFAULT_EXPIRE);
        return baseMapper.getDetail(id);
    }
}
