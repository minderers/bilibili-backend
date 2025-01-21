package top.dl.bilibili.backend.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: minder
 * @createTime: 2025/01/21 16:12
 * @description:
 **/
@Data
@EqualsAndHashCode(callSuper=true)
@Schema(name = "StarQuery",description = "查询评论")
public class StarQuery extends Query {
    @Schema(description = "用户id")
    private Integer userId;
}
