package top.dl.bilibili.backend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: minder
 * @createTime: 2025/01/17 20:09
 * @description:
 **/
@Data
@TableName("t_reply")
public class Reply {
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;
    private String content;
    private Integer userId;
    private Integer commentId;
    @TableField(value = "delete_flag",fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
