package top.dl.bilibili.backend.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.dl.bilibili.backend.model.entity.Category;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/17 21:31
 * @description:
 **/
@Data
@Schema(description = "分类vo")
public class CategoryVO {
    @Schema(description = "主键")
    private Integer pkId;
    @Schema(description = "标题")
    private String name;
    @Schema(description = "子分区")
    private List<CategoryChildrenVO> childrenList;
}
