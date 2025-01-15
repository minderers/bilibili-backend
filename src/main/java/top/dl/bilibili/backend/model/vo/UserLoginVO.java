package top.dl.bilibili.backend.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "用户登录 VO")
public class UserLoginVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8212240698099812005L;

    @Schema(description = "是否绑定手机号")
    private Integer isBind;

    @Schema(description = "类型")
    private Integer type;

    @Schema(description = "用户ID")
    private Integer userId;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "令牌")
    private String accessToken;

    @Schema(description = "OpenId")
    private String openId;

}
