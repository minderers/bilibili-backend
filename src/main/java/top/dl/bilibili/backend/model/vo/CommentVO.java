package top.dl.bilibili.backend.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/17 20:20
 * @description:
 **/
@Data
@Schema(description = "评论vo")
public class CommentVO {
    @Schema(description = "主键")
    private Integer pkId;
    @Schema(description = "内容")
    private String content;
    @Schema(description = "评论人")
    private String nickname;
    @Schema(description = "回复列表")
    private List<ReplyVO> replyVOList;
    @Schema(description = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;
}
