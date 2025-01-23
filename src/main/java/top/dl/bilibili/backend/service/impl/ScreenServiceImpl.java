package top.dl.bilibili.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.dl.bilibili.backend.mapper.ScreenMapper;
import top.dl.bilibili.backend.model.entity.Screen;
import top.dl.bilibili.backend.service.ScreenService;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/18 20:37
 * @description:
 **/
@Slf4j
@Service
public class ScreenServiceImpl extends ServiceImpl<ScreenMapper, Screen> implements ScreenService {
    @Resource
    private ScreenMapper screenMapper;
    @Override
    public List<Screen> selectScreen(Integer videoId) {
        return screenMapper.selectList(new LambdaQueryWrapper<Screen>().eq(Screen::getVideoId,videoId));
    }

    @Override
    public Screen deleteScreen(Integer id) {
        Screen screen = screenMapper.selectById(id);
        baseMapper.deleteById(id);
        return null;
    }
}
