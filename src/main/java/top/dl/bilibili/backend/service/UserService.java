package top.dl.bilibili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.dl.bilibili.backend.model.dto.UserEditDTO;
import top.dl.bilibili.backend.model.entity.User;
import top.dl.bilibili.backend.model.vo.UserInfoVO;

/**
 * @author: minder
 * @createTime: 2025/01/16 20:15
 * @description:
 **/
public interface UserService extends IService<User> {

    /**
     * 用户信息
     *
     * @return {@link UserInfoVO}
     */

    UserInfoVO userInfo();

    /**
     * 更新信息
     *
     * @param userEditDTO 用户编辑 DTO
     * @return UserInfoVO
     */

    UserInfoVO updateInfo(UserEditDTO userEditDTO);
}
