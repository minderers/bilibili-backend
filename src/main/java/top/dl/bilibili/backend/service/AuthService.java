package top.dl.bilibili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.dl.bilibili.backend.model.entity.User;
import top.dl.bilibili.backend.model.vo.UserLoginVO;
import top.dl.bilibili.backend.model.vo.UserRegisterVO;

/**
 * @author: minder
 * @createTime: 2025/01/16 18:51
 * @description:
 **/
public interface AuthService extends IService<User> {
    /**
     * 登录
     *
     * @param nickname 账户名
     * @param password 密码
     * @return {@link UserLoginVO}
     */
    UserRegisterVO register(String nickname, String password);

    /**
     * 登录
     *
     * @param nickname 账户名
     * @param password 密码
     * @return {@link UserLoginVO}
     */
    UserLoginVO loginByPassword(String nickname, String password);

    /**
     * 绑定手机号
     *
     * @param phone 电话
     * @param code 验证码
     * @param accessToken 访问令牌
     */
    void bindPhone(String phone,String code,String accessToken);
}
