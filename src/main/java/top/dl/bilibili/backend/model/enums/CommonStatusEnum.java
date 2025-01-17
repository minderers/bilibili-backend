package top.dl.bilibili.backend.model.enums;

import lombok.Getter;

/**
 * @author: minder
 * @createTime: 2024/11/17 14:28
 * @description:
 **/
@Getter
public enum CommonStatusEnum {
    NO(0,"否"),
    YES(1,"是"),
    ;

    private final Integer status;
    private final String desc;

    CommonStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
