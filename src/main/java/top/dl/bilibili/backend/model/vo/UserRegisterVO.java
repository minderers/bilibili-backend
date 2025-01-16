package top.dl.bilibili.backend.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author: minder
 * @createTime: 2025/01/16 19:20
 * @description:
 **/
@Data
@Schema(description = "用户注册vo")
public class UserRegisterVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8212240698099812005L;

    @Schema(description = "用户ID")
    public Integer pkId;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "账户名")
    private String nickname;

}
