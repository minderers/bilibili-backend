package top.dl.bilibili.backend.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.dl.bilibili.backend.common.cache.RequestContext;
import top.dl.bilibili.backend.common.cache.TokenStoreCache;
import top.dl.bilibili.backend.common.constant.Constant;
import top.dl.bilibili.backend.common.exception.ErrorCode;
import top.dl.bilibili.backend.common.exception.ServerException;
import top.dl.bilibili.backend.model.vo.UserLoginVO;
import top.dl.bilibili.backend.service.AuthService;
import top.dl.bilibili.backend.utils.JwtUtil;


/**
 * @author: minder
 * @createTime: 2024/11/15 19:31
 * @description:
 **/
@Slf4j
@AllArgsConstructor
@Component
public class TokenInterceptor implements HandlerInterceptor {
    private final TokenStoreCache tokenStoreCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取token
        String accessToken = JwtUtil.getAccessToken(request);
        if (StringUtils.isBlank(accessToken)){
            throw new ServerException(ErrorCode.UNAUTHORIZED);
        }
        //验证token
        if (!JwtUtil.validate(accessToken)){
            throw new ServerException(ErrorCode.UNAUTHORIZED);
        }
        //验证登录状态是否正常
        UserLoginVO user = tokenStoreCache.getUser(accessToken);
        if (ObjectUtils.isEmpty(user)){
            throw new ServerException(ErrorCode.LOGIN_STATUS_EXPIRE);
        }
        //保存用户id到上下文
        RequestContext.put(Constant.USER_ID,user.getPkId());
        return true;
    }
}

