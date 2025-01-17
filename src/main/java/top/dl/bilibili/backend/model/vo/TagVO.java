package top.dl.bilibili.backend.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: minder
 * @createTime: 2025/01/17 21:13
 * @description:
 **/
@Data
@Schema(description = "标签vo")
public class TagVO {
    @Schema(description = "主键")
    private Integer pkId;
    @Schema(description = "标题")
    private String name;
}
