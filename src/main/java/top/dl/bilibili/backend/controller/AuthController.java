package top.dl.bilibili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.dl.bilibili.backend.common.result.Result;
import top.dl.bilibili.backend.model.vo.UserLoginVO;
import top.dl.bilibili.backend.model.vo.UserRegisterVO;
import top.dl.bilibili.backend.service.AuthService;

/**
 * @author: minder
 * @createTime: 2025/01/16 19:34
 * @description:
 **/
@RestController
@RequestMapping("/auth")
@Tag(name = "认证接口")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "注册")
    public Result<UserRegisterVO> register(@RequestParam("nickname") String nickname,@RequestParam("password") String password) {
        return Result.ok(authService.register(nickname, password));
    }

    @PostMapping("/login")
    @Operation(summary = "密码登录")
    public Result<UserLoginVO> loginByPassword(@RequestParam("nickname") String nickname, @RequestParam("password") String password) {
        return Result.ok(authService.loginByPassword(nickname, password));
    }


    @PostMapping("/bindPhone")
    @Operation(summary =  "绑定手机号")
    public Result<String> bindPhone(@RequestParam("phone") String phone, @RequestParam("code") String code, @RequestHeader("Authorization") String accessToken){
        authService.bindPhone(phone, code, accessToken);
        return Result.ok();
    }

    @PostMapping("/loginByPhone")
    @Operation(summary = "手机号登录")
    public Result<UserLoginVO> loginByPhone(@RequestParam("phone") String phone, @RequestParam("code") String code){
        return Result.ok(authService.loginByPhone(phone, code));
    }
}
