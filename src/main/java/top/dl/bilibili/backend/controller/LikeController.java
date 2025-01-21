package top.dl.bilibili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.dl.bilibili.backend.common.result.PageResult;
import top.dl.bilibili.backend.common.result.Result;
import top.dl.bilibili.backend.model.entity.Like;
import top.dl.bilibili.backend.model.entity.User_Star;
import top.dl.bilibili.backend.model.query.StarQuery;
import top.dl.bilibili.backend.model.vo.LikeVO;
import top.dl.bilibili.backend.model.vo.StarVO;
import top.dl.bilibili.backend.service.LikeService;

/**
 * @author: minder
 * @createTime: 2025/01/21 19:47
 * @description:
 **/
@RestController
@RequestMapping("/like")
@Tag(name = "点赞接口")
@AllArgsConstructor
public class LikeController {
    private final LikeService likeService;
    @PostMapping("/list")
    @Operation(summary = "查询点赞列表")
    public Result<PageResult<LikeVO>> getLikeList(@RequestBody @Valid StarQuery query) {
        return Result.ok(likeService.getLikeList(query));
    }
    @PostMapping("/toggle")
    @Operation(summary = "添加删除点赞")
    public Result<Like> toggleStar(@RequestParam("userId") Integer userId, @RequestParam("videoId") Integer videoId) {
        return Result.ok(likeService.addLike(userId, videoId));
    }
}
