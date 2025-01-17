package top.dl.bilibili.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.dl.bilibili.backend.mapper.CategoryMapper;
import top.dl.bilibili.backend.model.entity.Category;
import top.dl.bilibili.backend.model.vo.CategoryChildrenVO;
import top.dl.bilibili.backend.model.vo.CategoryVO;
import top.dl.bilibili.backend.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: minder
 * @createTime: 2025/01/17 21:35
 * @description:
 **/
@Service
@Slf4j
@AllArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public List<CategoryVO> getCategoryList() {
        // 查询所有分类，过滤 delete_flag 为 0 的记录
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_flag", 0);
        // 查询所有分类
        List<Category> categories = list();

        // 构建分类树
        return buildCategoryTreeVO(categories);
    }

    private List<CategoryVO> buildCategoryTreeVO(List<Category> categories) {
        // 过滤出顶级分类（parentId 为 0）
        List<CategoryVO> rootCategories = categories.stream()
                .filter(category -> category.getParentId() == 0)
                .map(this::convertToCategoryVO)
                .collect(Collectors.toList());

        // 递归构建子分类
        rootCategories.forEach(root -> {
            root.setChildrenList(getChildrenVO(root.getPkId(), categories));
        });

        return rootCategories;
    }

    private List<CategoryChildrenVO> getChildrenVO(Integer parentId, List<Category> categories) {
        return categories.stream()
                .filter(category -> category.getParentId().equals(parentId))
                .map(this::convertToCategoryChildrenVO)
                .collect(Collectors.toList());
    }

    private CategoryVO convertToCategoryVO(Category category) {
        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setPkId(category.getPkId());
        categoryVO.setName(category.getName());
        return categoryVO;
    }

    private CategoryChildrenVO convertToCategoryChildrenVO(Category category) {
        CategoryChildrenVO categoryChildrenVO = new CategoryChildrenVO();
        categoryChildrenVO.setPkId(category.getPkId());
        categoryChildrenVO.setName(category.getName());
        return categoryChildrenVO;
    }
}
