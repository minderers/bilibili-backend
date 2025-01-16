package top.dl.bilibili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import top.dl.bilibili.backend.common.cache.RedisCache;
import top.dl.bilibili.backend.common.cache.RedisKeys;
import top.dl.bilibili.backend.common.cache.TokenStoreCache;
import top.dl.bilibili.backend.common.exception.ErrorCode;
import top.dl.bilibili.backend.common.exception.ServerException;
import top.dl.bilibili.backend.common.result.Result;
import top.dl.bilibili.backend.mapper.UserMapper;
import top.dl.bilibili.backend.model.entity.User;
import top.dl.bilibili.backend.model.vo.UserLoginVO;
import top.dl.bilibili.backend.model.vo.UserRegisterVO;
import top.dl.bilibili.backend.service.AuthService;
import top.dl.bilibili.backend.utils.CommonUtils;
import top.dl.bilibili.backend.utils.JwtUtil;

/**
 * @author: minder
 * @createTime: 2025/01/16 18:58
 * @description:
 **/

@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl extends ServiceImpl<UserMapper, User> implements AuthService {
    private final RedisCache redisCache;
    private final TokenStoreCache tokenStoreCache;

    @Override
    public UserRegisterVO register(String nickname, String password) {
        User user=baseMapper.getByNickname(nickname);
        if(ObjectUtils.isEmpty(user)){
            user =new User();
            user.setNickname(nickname);
            user.setPassword(password);
            user.setGender(0);
            user.setAvatar("默认头像的url");
            user.setRemark("这个人很懒，什么都没有写");
            baseMapper.insert(user);
        }
        UserRegisterVO userRegisterVO=new UserRegisterVO();
        userRegisterVO.setPkId(user.getPkId());
        userRegisterVO.setNickname(user.getNickname());
        return userRegisterVO;
    }

    @Override
    public UserLoginVO loginByPassword(String nickname, String password) {
        User user=baseMapper.getByNickname(nickname);
        if (user == null) {
            // 用户不存在，抛出异常
            throw new ServerException(ErrorCode.USER_NOT_EXIST);
        }
        if(password.equals(user.getPassword())){
            // 构造token
            String accessToken = JwtUtil.createToken(user.getPkId());
            //构造登录返回vo
            UserLoginVO userLoginVO=new UserLoginVO();
            userLoginVO.setPkId(user.getPkId());
            userLoginVO.setPhone(user.getPhone());
            userLoginVO.setNickname(user.getNickname());
            userLoginVO.setAccessToken(accessToken);
            tokenStoreCache.saveUser(accessToken,userLoginVO);
            return userLoginVO;
        }
        throw new ServerException(ErrorCode.PASSWORD_WARN);
    }

    @Override
    public void bindPhone(String phone, String code, String accessToken) {
        // 简单校验手机号合法性
        if(!CommonUtils.checkPhone(phone)){
            throw new ServerException(ErrorCode.PARAMS_ERROR);
        }
        // 获取手机验证码，校验验证码正确性
        String redisCode=redisCache.get(RedisKeys.getSmsKey(phone)).toString();
        if(ObjectUtils.isEmpty(redisCode)||!redisCode.equals(code)){
            throw new ServerException(ErrorCode.SMS_CODE_ERROR);
        }
        // 删除验证码缓存
        redisCache.delete(RedisKeys.getSmsKey(phone));
        // 获取当前用户信息
        User userByPhone=baseMapper.getByPhone(phone);
        // 获取当前登录的用户信息
        UserLoginVO userLogin=tokenStoreCache.getUser(accessToken);
        // 判断新手机号是否存在用户
        if(ObjectUtils.isNotEmpty(userByPhone)){
            // 存在用户，并且不是当前用户，抛出异常
            if(!userLogin.getPkId().equals(userByPhone.getPkId())){
                throw new ServerException(ErrorCode.PHONE_IS_EXIST);
            }
            // 存在用户，并且是当前用户，提示用户手机号相同
            if(userLogin.getPhone().equals(phone)){
                throw new ServerException(ErrorCode.THE_SAME_PHONE);
            }
        }
        // 重新设置手机号
        User user=baseMapper.selectById(userLogin.getPkId());
        user.setPhone(phone);
        if(baseMapper.updateById(user)<1){
            throw new ServerException(ErrorCode.OPERATION_FAIL);
        }
    }
}
