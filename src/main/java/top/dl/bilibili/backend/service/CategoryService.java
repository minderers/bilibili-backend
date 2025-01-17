package top.dl.bilibili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.dl.bilibili.backend.model.entity.Category;
import top.dl.bilibili.backend.model.vo.CategoryVO;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/17 21:34
 * @description:
 **/
public interface CategoryService extends IService<Category> {
    List<CategoryVO> getCategoryList();
}
