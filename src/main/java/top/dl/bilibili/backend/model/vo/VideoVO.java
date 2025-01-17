package top.dl.bilibili.backend.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: minder
 * @createTime: 2025/01/17 19:09
 * @description:
 **/
@Data
@Schema(description = "视频列表vo")
public class VideoVO {
    @Schema(description = "主键")
    private Integer pkId;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "封面")
    private String cover;
    @Schema(description = "播放量")
    private Integer browseNum;
    @Schema(description = "作者")
    private String nickname;
    @Schema(description = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;
}
