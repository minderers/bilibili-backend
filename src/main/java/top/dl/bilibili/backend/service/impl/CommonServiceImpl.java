package top.dl.bilibili.backend.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.dl.bilibili.backend.common.cache.RedisCache;
import top.dl.bilibili.backend.common.cache.RedisKeys;
import top.dl.bilibili.backend.common.config.CloopenConfig;
import top.dl.bilibili.backend.common.config.OssConfig;
import top.dl.bilibili.backend.common.exception.ErrorCode;
import top.dl.bilibili.backend.common.exception.ServerException;
import top.dl.bilibili.backend.service.CommonService;
import top.dl.bilibili.backend.utils.CommonUtils;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

/**
 * @author: minder
 * @createTime: 2025/01/17 22:29
 * @description:
 **/
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {
    @Resource
    private CloopenConfig cloopenConfig;

    @Resource
    private RedisCache redisCache;

    @Resource
    private OssConfig ossConfig;
    private static final String[] IMAGE_TYPES = new String[]{"jpg", "jpeg", "png", "gif",".bmp"};

    @Override
    public void sendSms(String phone){
        //验证手机号合法性
        if (!CommonUtils.checkPhone(phone)){
            throw new ServerException(ErrorCode.PARAMS_ERROR);
        }
        //生成随机验证码
        int code = CommonUtils.generateCode();
        //redis缓存验证码
        redisCache.set(RedisKeys.getSmsKey(phone),code,60);
        //调用内部方法
        boolean result = cloopenSendSms(phone,code);
        if (result){
            log.info("============短信发送成功============");
        }
    }

    public String upload(MultipartFile file){
        String returnImgUrl;

        boolean isLegal = false;
        for (String type : IMAGE_TYPES) {
            if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(),type)){
                isLegal = true;
                break;
            }
        }
        if (!isLegal){
            throw new ServerException("图片格式不正确");
        }
        //获取文件原名称
        String originalFilename = file.getOriginalFilename();
        //获取文件类型
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        //新文件名称
        String newFileName = UUID.randomUUID().toString() + fileType;
        //构建日期路径，例如：OSS目标文件夹/2024/04/31文件名
        String filePath = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        //文件上传的路径地址
        String uploadUrl = filePath + "/" + newFileName;
        //获取文件输入流
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        }catch (IOException e){
            e.printStackTrace();
        }
        /**
         * 现在阿里云OSS默认图片上传ContentType是image/jpeg
         * 也就是说，获取图片链接后，图片是下载链接，而并非在线浏览链接
         * 因此，这里在上传的时候要解决ContentType的问题，将其改完image/jpg
         */
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("image/jpg");

        //读取配置文件中的属性
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
        String bucketName = ossConfig.getBucketName();
        //创建OssClient
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //文件上传至阿里云oss
        ossClient.putObject(bucketName, newFileName, inputStream,meta);
        //获取文件上传后的图片返回地址
        returnImgUrl = "https://" + bucketName + "." + endpoint + "/" + uploadUrl;
        return returnImgUrl;
    }

    /**
     * cloopen 发送短信
     *
     * @param phone 电话
     * @param code 验证码
     * @return  boolean
     */

    private boolean cloopenSendSms(String phone,int code){
        try {
            log.info("=========== 创建短信发送通道中 =========== \nphone is {},code is {}",phone,code);
            String serverIp = cloopenConfig.getServerIp();
            //请求端口
            String serverPort = cloopenConfig.getPort();
            //主账号，登录云通讯网站后，可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
            String accountSId = cloopenConfig.getAccountSId();
            String accountToken = cloopenConfig.getAccountToken();
            //请使用管理控制台中已创建应用的APPID
            String appId = cloopenConfig.getAppId();
            CCPRestSmsSDK sdk = new CCPRestSmsSDK();
            sdk.init(serverIp,serverPort);
            sdk.setAccount(accountSId,accountToken);
            sdk.setAppId(appId);
            sdk.setBodyType(BodyType.Type_JSON);
            String templateId = cloopenConfig.getTemplateId();
            String[] datas = {String.valueOf(code),"1"};
            HashMap<String, Object> result = sdk.sendTemplateSMS(phone,templateId,datas,"1234",UUID.randomUUID().toString());
            if ("000000".equals(result.get("statusCode"))){
                //正常返回输出data包体信息（map）
                HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
                Set<String> keySet = data.keySet();
                for (String key : keySet){
                    Object object  = data.get(key);
                    log.info("{} = {}", key, object);
                }
            }else{
                log.error("错误码={} 错误信息= {}",result.get("statusCode"),result.get("statusMsg"));
                throw new ServerException(ErrorCode.CODE_SEND_FAIL);
            }
        }catch (Exception e){
            throw new ServerException(ErrorCode.CODE_SEND_FAIL);
        }
        return true;
    }
}
