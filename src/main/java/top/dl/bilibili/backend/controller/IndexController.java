package top.dl.bilibili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.dl.bilibili.backend.common.result.PageResult;
import top.dl.bilibili.backend.common.result.Result;
import top.dl.bilibili.backend.model.entity.Swiper;
import top.dl.bilibili.backend.model.entity.Video;
import top.dl.bilibili.backend.model.query.CommentQuery;
import top.dl.bilibili.backend.model.query.Query;
import top.dl.bilibili.backend.model.vo.CategoryVO;
import top.dl.bilibili.backend.model.vo.CommentVO;
import top.dl.bilibili.backend.model.vo.VideoDetailVO;
import top.dl.bilibili.backend.model.vo.VideoVO;
import top.dl.bilibili.backend.service.CategoryService;
import top.dl.bilibili.backend.service.CommentService;
import top.dl.bilibili.backend.service.SwiperService;
import top.dl.bilibili.backend.service.VideoService;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/01/17 18:38
 * @description:
 **/
@RestController
@RequestMapping("/index")
@Tag(name = "首页接口")
@AllArgsConstructor
public class IndexController {
    private final SwiperService swiperService;
    private final VideoService videoService;
    private final CommentService commentService;
    private final CategoryService categoryService;
    @GetMapping("/swiper")
    @Operation(summary = "轮播图")
    public Result<List<Swiper>> swiperList() {
        return Result.ok(swiperService.getSwiperList());
    }
    @PostMapping("/videoList")
    @Operation(summary = "分页查询视频列表")
    public Result<PageResult<VideoVO>> videoList(@RequestBody @Valid Query query) {
        return Result.ok(videoService.getVideoList(query));
    }
    @GetMapping("/categoryList")
    @Operation(summary = "分类列表")
    public Result<List<CategoryVO>> categoryList() {
        return Result.ok(categoryService.getCategoryList());
    }
    @GetMapping("/videoDetail")
    @Operation(summary = "查询视频详情")
    public Result<VideoDetailVO> videoDetail(@RequestParam("videoId") Integer id) {
        return Result.ok(videoService.detail(id));
    }
    @PostMapping("/videoDetail/commentList")
    @Operation(summary = "查询视频评论和回复")
    public Result<PageResult<CommentVO>> videoDetailCommentList(@RequestBody @Valid CommentQuery query) {
        return Result.ok(commentService.getCommentList(query));
    }
}
