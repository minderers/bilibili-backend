package top.dl.bilibili.backend.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author: minder
 * @createTime: 2025/01/16 20:13
 * @description:
 **/
@Data
@Schema(description = "用户信息")
public class UserInfoVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3509639455772804623L;

    @Schema(description = "主键")
    private Integer pkId;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "生日")
    private String birth;

    @Schema(description = "描述")
    private String remark;

    @Schema(description = "关注")
    private String attentNum;

    @Schema(description = "粉丝")
    private String fanNum;
}
