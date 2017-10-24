package com.baiying.hu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jmx.
 * Date 2017/7/5.
 */
public class CookieUtils {
    private static final Logger LOG = LoggerFactory.getLogger(CookieUtils.class);

    private CookieUtils() {
        // no need to implement
    }

    /**
     * 设置cookie
     *
     * @param response response
     * @param name     cookie名字
     * @param value    cookie值
     * @param maxAge   cookie生命周期  以秒为单位
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge, String domain) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setDomain(domain);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 根据名字获取cookie
     *
     * @param request request
     * @param name    cookie名字
     * @return Cookie
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        return cookieMap.getOrDefault(name, null);
    }

    /**
     * 将cookie封装到Map里面
     *
     * @param request request
     * @return Map
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 清空本地cookie
     *
     * @param request request
     */
    public static void clearCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        try {
            for (Cookie cook : cookies) {
                Cookie cookie = new Cookie(cook.getName(), null);
                cookie.setMaxAge(0);
                cookie.setPath(".me");
                response.addCookie(cookie);
            }
        } catch (Exception ex) {
            LOG.error("删除Cookies发生异常:{}", ex);
        }
    }

    public static String getCookieValue(HttpServletRequest request, String key) {
//        Cookie[] cookies = request.getCookies();
        String token = request.getHeader("COFFEE_TOKEN");
        if (!StringUtils.isEmpty(token)) {
            return token;
        } else {
            return "";
        }
    }

    public static String getAdminCookieValue(HttpServletRequest request, String key) {
//        Cookie[] cookies = request.getCookies();
        String token = request.getHeader("ADMIN_TOKEN");
        if (!StringUtils.isEmpty(token)) {
            return token;
        } else {
            return "";
        }
    }

    public static boolean setCookieValue(HttpServletRequest request, HttpServletResponse response, String name, String value) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return false;
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(name)) {
                    cookie.setValue(value);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
        return true;
    }

    public static void setTokenToCookie(HttpServletResponse response, String token) {
//        Cookie cookie = new Cookie("COFFEE_TOKEN", token);
//        cookie.setMaxAge(1800);
        response.setHeader("COFFEE_TOKEN", token);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setAdminTokenToCookie(HttpServletResponse response, String token) {
//        Cookie cookie = new Cookie("COFFEE_TOKEN", token);
//        cookie.setMaxAge(1800);
        response.setHeader("ADMIN_TOKEN", token);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
