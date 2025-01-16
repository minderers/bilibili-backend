package top.dl.bilibili.backend.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.dl.bilibili.backend.model.dto.UserEditDTO;
import top.dl.bilibili.backend.model.entity.User;
import top.dl.bilibili.backend.model.vo.UserInfoVO;

/**
 * @author: minder
 * @createTime: 2025/01/16 20:17
 * @description:
 **/
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserInfoVO convert(User user);

    User convert(UserEditDTO dto);

}
