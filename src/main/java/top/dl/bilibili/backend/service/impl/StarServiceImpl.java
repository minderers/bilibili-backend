package top.dl.bilibili.backend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.dl.bilibili.backend.common.result.PageResult;
import top.dl.bilibili.backend.mapper.StarMapper;
import top.dl.bilibili.backend.model.entity.User_Star;
import top.dl.bilibili.backend.model.query.StarQuery;
import top.dl.bilibili.backend.model.vo.StarVO;
import top.dl.bilibili.backend.service.StarService;

import java.util.Date;
import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/21 16:26
 * @description:
 **/
@Service
@Slf4j
public class StarServiceImpl extends ServiceImpl<StarMapper, User_Star> implements StarService {
    @Override
    public PageResult<StarVO> getStarList(StarQuery query) {
        Page<StarVO> page = new Page<>(query.getPage(), query.getLimit());
        List<StarVO> list = baseMapper.selectStarByUserId(page, query);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public User_Star addStar(Integer userId, Integer videoId) {
        User_Star star = baseMapper.selectStarByUserIdAndVideoId(userId, videoId);
        if (star == null) {
            star = new User_Star();
            star.setUserId(userId);
            star.setVideoId(videoId);
            baseMapper.insert(star);
            return null;
        }else {
            removeById(star.getPkId());
            return null;
        }
    }
}
