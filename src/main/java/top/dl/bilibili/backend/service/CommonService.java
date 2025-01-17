package top.dl.bilibili.backend.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: minder
 * @createTime: 2025/01/17 22:29
 * @description:
 **/
public interface CommonService {
    /**
     * 发送短信
     *
     * @param phone 手机号
     */
    void sendSms(String phone);

    /**
     * 文件上传
     *
     * @param file 文件
     * @return 上传后的 url
     */

    String upload(MultipartFile file);
}
