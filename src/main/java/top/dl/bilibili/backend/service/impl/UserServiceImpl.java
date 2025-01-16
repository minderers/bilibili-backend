package top.dl.bilibili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.dl.bilibili.backend.common.cache.RequestContext;
import top.dl.bilibili.backend.common.exception.ErrorCode;
import top.dl.bilibili.backend.common.exception.ServerException;
import top.dl.bilibili.backend.convert.UserConvert;
import top.dl.bilibili.backend.mapper.UserMapper;
import top.dl.bilibili.backend.model.dto.UserEditDTO;
import top.dl.bilibili.backend.model.entity.User;
import top.dl.bilibili.backend.model.vo.UserInfoVO;
import top.dl.bilibili.backend.service.UserService;

/**
 * @author: minder
 * @createTime: 2025/01/16 20:15
 * @description:
 **/
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public UserInfoVO userInfo(){
        Integer userId = RequestContext.getUserId();
        //查询数据库
        User user = baseMapper.selectById(userId);
        if(user == null){
            log.error("用户不存在，userId:{}",userId);
            throw new ServerException(ErrorCode.USER_NOT_EXIST);
        }
        UserInfoVO userInfoVO = UserConvert.INSTANCE.convert(user);
        log.info(userInfoVO.toString());
        return userInfoVO;
    }

    @Override
    public UserInfoVO updateInfo(UserEditDTO userEditDTO){
        Integer userId = RequestContext.getUserId();
        userEditDTO.setPkId(userId);
        User user = UserConvert.INSTANCE.convert(userEditDTO);
        if (user.getPkId() == null) {
            throw new ServerException(ErrorCode.PARAMS_ERROR);
        }
        try {
            if (baseMapper.updateById(user) < 1) {
                throw new ServerException("修改失败");
            }
        }catch (Exception e){
            throw new ServerException(e.getMessage());
        }
        return this.userInfo();
    }
}
