package top.dl.bilibili.backend.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import top.dl.bilibili.backend.model.entity.User;

/**
 * @author: minder
 * @createTime: 2025/01/16 18:48
 * @description:
 **/
public interface UserMapper extends BaseMapper<User> {
    default User getByNickname(String nickname) {
        return this.selectOne(new LambdaQueryWrapper<User>().eq(User::getNickname,nickname));
    }
    default User getByPhone(String phone){
        return this.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone,phone));
    }
}
