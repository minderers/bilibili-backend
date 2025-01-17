package top.dl.bilibili.backend.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.dl.bilibili.backend.model.entity.Tag;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/17 21:00
 * @description:
 **/
@Data
@Schema(description = "视频列表vo")
public class VideoDetailVO {
    @Schema(description = "主键")
    private Integer pkId;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "封面")
    private String cover;
    @Schema(description = "分类")
    private String name;
    @Schema(description = "视频")
    private String url;
    @Schema(description = "简介")
    private String introduction;
    @Schema(description = "标签")
    private List<Tag> tagList;
    @Schema(description = "点赞量")
    private Integer likeNum;
    @Schema(description = "收藏量")
    private Integer starNum;
    @Schema(description = "播放量")
    private Integer browseNum;
    @Schema(description = "作者")
    private String nickname;
    @Schema(description = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;
}
