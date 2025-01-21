package top.dl.bilibili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.dl.bilibili.backend.common.result.PageResult;
import top.dl.bilibili.backend.model.entity.Like;
import top.dl.bilibili.backend.model.query.StarQuery;
import top.dl.bilibili.backend.model.vo.LikeVO;

/**
 * @author: minder
 * @createTime: 2025/01/21 19:39
 * @description:
 **/
public interface LikeService extends IService<Like> {
    PageResult<LikeVO> getLikeList(StarQuery query);
    Like addLike(Integer userId,Integer videoId);
}
