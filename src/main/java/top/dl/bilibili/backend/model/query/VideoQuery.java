package top.dl.bilibili.backend.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: minder
 * @createTime: 2025/01/23 14:13
 * @description:
 **/
@Data
@EqualsAndHashCode(callSuper=true)
@Schema(name = "VideoQuery",description = "分区查询")
public class VideoQuery extends Query {
    @Schema(description = "分区id")
    private Integer categoryId;
    @Schema(description = "用户id")
    private Integer userId;
}
