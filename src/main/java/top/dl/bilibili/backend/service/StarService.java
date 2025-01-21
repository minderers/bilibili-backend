package top.dl.bilibili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.dl.bilibili.backend.common.result.PageResult;
import top.dl.bilibili.backend.model.entity.User_Star;
import top.dl.bilibili.backend.model.query.StarQuery;
import top.dl.bilibili.backend.model.vo.StarVO;

/**
 * @author: minder
 * @createTime: 2025/01/21 16:24
 * @description:
 **/
public interface StarService extends IService<User_Star> {
    PageResult<StarVO> getStarList(StarQuery query);
    User_Star addStar(Integer userId, Integer videoId);
}
