package top.dl.bilibili.backend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: minder
 * @createTime: 2025/01/17 17:33
 * @description:
 **/
@Data
@TableName("t_video")
public class Video {
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;
    private Integer categoryId;
    private String title;
    private String cover;
    private String url;
    private Integer type;
    private String introduction;
    private Integer userId;
    /**
     * @see top.dl.bilibili.backend.model.enums.ResourceStatusEnum
     */
    private Integer state;
    private Integer likeNum;
    private Integer starNum;
    private Integer browseNum;
    @TableField(value = "delete_flag",fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
