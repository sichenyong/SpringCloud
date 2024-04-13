package com.atguigu.cloud.controller.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * @Author：sichenyong
 * @Email: sichenyongwork@163.com
 * @Package：com.atguigu.cloud.controller.handler
 * @Project：springcloud-project
 * @Date：2024/3/23 17:13
 * @description：
 */
@Component
public class MyRequestOriginParser implements RequestOriginParser
{
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameter("serverName");
    }
}
