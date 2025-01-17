package top.dl.bilibili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.dl.bilibili.backend.model.entity.Swiper;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/17 18:28
 * @description:
 **/
public interface SwiperService extends IService<Swiper> {
    List<Swiper> getSwiperList();
}
