package top.dl.bilibili.backend.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.dl.bilibili.backend.model.entity.Swiper;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/17 18:27
 * @description:
 **/
public interface SwiperMapper extends BaseMapper<Swiper> {
    default List<Swiper> selectAll() {
        QueryWrapper<Swiper> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", 2).eq("is_swiper", 1).eq("delete_flag", 0);
        return selectList(queryWrapper);
    }
}
