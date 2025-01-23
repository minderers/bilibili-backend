package top.dl.bilibili.backend.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: minder
 * @createTime: 2025/01/23 17:40
 * @description:
 **/
@Data
@EqualsAndHashCode(callSuper=true)
@Schema(name = "UserCommentQuery",description = "查询用户评论")
public class UserCommentQuery extends Query {
    @Schema(description = "用户id")
    private Integer userId;
}
