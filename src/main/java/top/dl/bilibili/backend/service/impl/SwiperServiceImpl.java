package top.dl.bilibili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.dl.bilibili.backend.mapper.SwiperMapper;
import top.dl.bilibili.backend.model.entity.Swiper;
import top.dl.bilibili.backend.service.SwiperService;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/17 18:32
 * @description:
 **/
@Service
@Slf4j
public class SwiperServiceImpl extends ServiceImpl<SwiperMapper, Swiper> implements SwiperService {
    @Resource
    private SwiperMapper swiperMapper;
    @Override
    public List<Swiper> getSwiperList() {
        return swiperMapper.selectAll();
    }
}
