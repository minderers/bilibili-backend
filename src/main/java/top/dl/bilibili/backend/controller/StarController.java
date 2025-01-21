package top.dl.bilibili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.dl.bilibili.backend.common.result.PageResult;
import top.dl.bilibili.backend.common.result.Result;
import top.dl.bilibili.backend.model.entity.User_Star;
import top.dl.bilibili.backend.model.query.StarQuery;
import top.dl.bilibili.backend.model.vo.StarVO;
import top.dl.bilibili.backend.service.StarService;

/**
 * @author: minder
 * @createTime: 2025/01/18 19:19
 * @description:
 **/
@RestController
@RequestMapping("/star")
@Tag(name = "收藏接口")
@AllArgsConstructor
public class StarController {
    private final StarService starService;
    @PostMapping("/list")
    @Operation(summary = "查询收藏列表")
    public Result<PageResult<StarVO>> getStarList(@RequestBody @Valid StarQuery query) {
        return Result.ok(starService.getStarList(query));
    }
    @PostMapping("/toggle")
    @Operation(summary = "添加删除收藏")
    public Result<User_Star> toggleStar(@RequestParam("userId") Integer userId, @RequestParam("videoId") Integer videoId) {
        return Result.ok(starService.addStar(userId, videoId));
    }
}
