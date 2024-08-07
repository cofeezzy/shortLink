package com.zzy.shortLink.project.toolkit;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;
import java.util.Date;
import java.util.Optional;

import static com.zzy.shortLink.project.common.constant.ShortLinkConstant.DEFAULT_LINK_CACHE_VALID_TIME;

/**
 * 短链接工具类
 */
public class LinkUtil {

    /**
     * 获取短链接缓存有效时间
     * @param validateDate 有效期时间
     * @return 有效期时间戳
     */
    public static long getLinkCacheValidTime(Date validateDate){
        return Optional.ofNullable(validateDate)
                .map(each -> DateUtil.between(new Date(), each, DateUnit.MS))
                .orElse(DEFAULT_LINK_CACHE_VALID_TIME);
    }

    /**
     * 获取请求的真实IP地址
     * @param request 请求
     * @return 用户真实IP
     */
    public static String getRealAddress(HttpServletRequest request){
        String ipAddress = request.getHeader("X-Forwarded-For");

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }

    /**
     * 获取用户访问的OS
     * @param request 请求
     * @return 访问的OS
     */
    public static String getOS(HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent");
        if(userAgent.toLowerCase().contains("windows")){
            return "Windows";
        }else if (userAgent.toLowerCase().contains("mac")) {
            return "Mac OS";
        } else if (userAgent.toLowerCase().contains("linux")) {
            return "Linux";
        } else if (userAgent.toLowerCase().contains("unix")) {
            return "Unix";
        } else if (userAgent.toLowerCase().contains("android")) {
            return "Android";
        } else if (userAgent.toLowerCase().contains("iphone")) {
            return "iOS";
        } else {
            return "Unknown";
        }
    }

    /**
     * 获取用户访问浏览器
     *
     * @param request 请求
     * @return 访问浏览器
     */
    public static String getBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.toLowerCase().contains("edg")) {
            return "Microsoft Edge";
        } else if (userAgent.toLowerCase().contains("chrome")) {
            return "Google Chrome";
        } else if (userAgent.toLowerCase().contains("firefox")) {
            return "Mozilla Firefox";
        } else if (userAgent.toLowerCase().contains("safari")) {
            return "Apple Safari";
        } else if (userAgent.toLowerCase().contains("opera")) {
            return "Opera";
        } else if (userAgent.toLowerCase().contains("msie") || userAgent.toLowerCase().contains("trident")) {
            return "Internet Explorer";
        } else {
            return "Unknown";
        }
    }

    /**
     * 获取用户访问设备
     *
     * @param request 请求
     * @return 访问设备
     */
    public static String getDevice(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.toLowerCase().contains("mobile")) {
            return "Mobile";
        }
        return "PC";
    }

    /**
     * 获取用户访问网络
     *
     * @param request 请求
     * @return 访问设备
     */
    public static String getNetwork(HttpServletRequest request) {
        String actualIp = getRealAddress(request);
        // 这里简单判断IP地址范围，您可能需要更复杂的逻辑
        // 例如，通过调用IP地址库或调用第三方服务来判断网络类型
        return actualIp.startsWith("192.168.") || actualIp.startsWith("10.") ? "WIFI" : "Mobile";
    }

    public static String extractDomain(String url){
        String domain = null;
        try {
            URI uri = new URI(url);
            String host = uri.getHost();
            if(StrUtil.isNotBlank(host)){
                domain = host;
                if(domain.startsWith("www.")){
                    domain = host.substring(4);
                }
            }
        }catch (Exception ignored){
        }
        return domain;
    }
}
