package top.dl.bilibili.backend.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: minder
 * @createTime: 2025/01/17 20:22
 * @description:
 **/
@Data
@Schema(description = "回复列表vo")
public class ReplyVO {
    @Schema(description = "主键")
    private Integer pkId;
    @Schema(description = "内容")
    private String content;
    @Schema(description = "回复人")
    private String nickname;
    @Schema(description = "评论id")
    private Integer commentId;
    @Schema(description = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;
}
