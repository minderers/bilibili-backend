package top.dl.bilibili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.dl.bilibili.backend.common.result.Result;
import top.dl.bilibili.backend.model.dto.UserEditDTO;
import top.dl.bilibili.backend.model.vo.UserInfoVO;
import top.dl.bilibili.backend.service.UserService;

/**
 * @author: minder
 * @createTime: 2025/01/16 20:18
 * @description:
 **/
@Slf4j
@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Tag(name = "个人中心")
public class UserController {
    private final UserService userService;

    @GetMapping("info")
    @Operation(summary = "查询用户信息")
    public Result<UserInfoVO> userInfo(){
        return Result.ok(userService.userInfo());
    }

    @PostMapping("update")
    @Operation(summary = "修改用户信息")
    public Result<UserInfoVO> update(@RequestBody UserEditDTO userEditDTO) {
        return Result.ok(userService.updateInfo(userEditDTO));
    }
}
