package top.dl.bilibili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.dl.bilibili.backend.common.result.Result;
import top.dl.bilibili.backend.service.CommonService;

/**
 * @author: minder
 * @createTime: 2025/01/17 22:30
 * @description:
 **/
@Tag(name = "基础服务")
@RestController
@RequestMapping("/common")
@AllArgsConstructor
public class CommonController {
    private final CommonService commonService;
    @PostMapping("/sendSms")
    @Operation(summary = "发送短信")
    public Result<Object> sendSms(@RequestParam("phone") String phone) {
        commonService.sendSms(phone);
        return Result.ok();
    }
    @PostMapping(value = "/upload/img")
    @Operation(summary = "图片上传")
    public Result<Object> upload(@RequestBody MultipartFile file) {
        return  Result.ok(commonService.upload(file));
    }
}
