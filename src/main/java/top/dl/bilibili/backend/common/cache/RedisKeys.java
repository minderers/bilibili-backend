package top.dl.bilibili.backend.common.cache;

public class RedisKeys {
    public static String getSmsKey(String phone) {
        return "sms:captcha:" + phone;
    }

    public static String getAccessTokenKey(String accessToken) {
        return "sys:access:token:" + accessToken;
    }

    public static String getUserIdKey(Integer id) {
        return "sys:userId:" + id;
    }

    public static String getVideoIdKey(Integer id){return "sys:videoId:" + id;}

}
