package top.dl.bilibili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.dl.bilibili.backend.model.entity.Screen;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/18 20:35
 * @description:
 **/
public interface ScreenService extends IService<Screen> {
    List<Screen> selectScreen(Integer videoId);
}
