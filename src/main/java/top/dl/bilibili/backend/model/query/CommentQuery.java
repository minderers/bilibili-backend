package top.dl.bilibili.backend.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: minder
 * @createTime: 2025/01/17 20:14
 * @description:
 **/
@Data
@EqualsAndHashCode(callSuper=true)
@Schema(name = "CommentQuery",description = "查询评论")
public class CommentQuery extends Query {
    @Schema(description = "视频id")
    private Integer videoId;
}
