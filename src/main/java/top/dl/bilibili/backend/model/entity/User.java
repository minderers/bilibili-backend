package top.dl.bilibili.backend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: minder
 * @createTime: 2025/01/16 18:38
 * @description:
 **/
@Data
@TableName("t_user")
public class User {
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;
    private String phone;
    private String avatar;
    private String nickname;
    private String password;
    private Integer gender;
    private String birth;
    private String remark;
    private Integer attentNum;
    private Integer fanNum;

    @TableField(value = "delete_flag",fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
