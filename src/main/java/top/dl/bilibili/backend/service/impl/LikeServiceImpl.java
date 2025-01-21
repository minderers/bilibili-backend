package top.dl.bilibili.backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.dl.bilibili.backend.common.exception.ErrorCode;
import top.dl.bilibili.backend.common.exception.ServerException;
import top.dl.bilibili.backend.common.result.PageResult;
import top.dl.bilibili.backend.mapper.LikeMapper;
import top.dl.bilibili.backend.model.entity.Like;
import top.dl.bilibili.backend.model.query.StarQuery;
import top.dl.bilibili.backend.model.vo.LikeVO;
import top.dl.bilibili.backend.service.LikeService;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/21 19:41
 * @description:
 **/
@Slf4j
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements LikeService {
    @Override
    public PageResult<LikeVO> getLikeList(StarQuery query) {
        Page<LikeVO> page = new Page<>(query.getPage(), query.getLimit());
        List<LikeVO> list = baseMapper.selectLikeByUserId(page, query);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public Like addLike(Integer userId, Integer videoId) {
        Like like = baseMapper.selectLikeByUserIdAndVideoId(userId, videoId);
        if (like == null) {
            like = new Like();
            like.setUserId(userId);
            like.setVideoId(videoId);
            baseMapper.insert(like);
            return null;
        }else {
            removeById(like.getPkId());
            throw  new ServerException(ErrorCode.OPERATION_FAIL);
        }
    }
}
