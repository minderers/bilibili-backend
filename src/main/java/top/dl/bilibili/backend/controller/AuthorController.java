package top.dl.bilibili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.dl.bilibili.backend.common.result.PageResult;
import top.dl.bilibili.backend.common.result.Result;
import top.dl.bilibili.backend.model.entity.Screen;
import top.dl.bilibili.backend.model.query.CommentQuery;
import top.dl.bilibili.backend.model.query.UserCommentQuery;
import top.dl.bilibili.backend.model.query.VideoQuery;
import top.dl.bilibili.backend.model.vo.CommentVO;
import top.dl.bilibili.backend.model.vo.VideoVO;
import top.dl.bilibili.backend.service.CommentService;
import top.dl.bilibili.backend.service.ScreenService;
import top.dl.bilibili.backend.service.VideoService;

/**
 * @author: minder
 * @createTime: 2025/01/23 14:19
 * @description:
 **/
@RestController
@RequestMapping("/author")
@Tag(name = "创作者中心接口")
@AllArgsConstructor
public class AuthorController {
    private final VideoService videoService;
    private final CommentService commentService;
    private final ScreenService screenService;

    @PostMapping("/draft")
    @Operation(summary = "分区视频")
    public Result<PageResult<VideoVO>> getCategoryVideoList(@RequestBody @Valid VideoQuery query){
        return Result.ok(videoService.getCategoryVideoList(query));
    }

    @PostMapping("/draftDelete")
    @Operation(summary = "删除稿件")
    public Result<VideoVO> delete(@RequestParam("videoId") Integer videoId){
        return Result.ok(videoService.delete(videoId));
    }

    @PostMapping("/commentDelete")
    @Operation(summary = "删除评论")
    public Result<CommentVO> deleteComment(@RequestParam("commentId") Integer commentId){
        return Result.ok(commentService.deleteComment(commentId));
    }

    @PostMapping("/screenDelete")
    @Operation(summary = "删除弹幕")
    public Result<Screen> deleteScreen(@RequestParam("screenId") Integer screenId){
        return Result.ok(screenService.deleteScreen(screenId));
    }

    @PostMapping("/commentList")
    @Operation(summary = "最新发布评论")
    public Result<PageResult<CommentVO>> getNewCommentList(@RequestBody @Valid UserCommentQuery query){
        return Result.ok(commentService.getNewCommentList(query));
    }
}
