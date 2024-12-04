package com.lizekai.wms.filter;

import com.alibaba.fastjson.JSON;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.LoginUser;
import com.lizekai.wms.enums.AppHttpCodeEnum;
import com.lizekai.wms.utils.JwtUtil;
import com.lizekai.wms.utils.RedisCache;
import com.lizekai.wms.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 自定义请求过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=request.getHeader("token");
        //没有token的请求直接放行
        if(!StringUtils.hasText(token)){
            filterChain.doFilter(request,response);
            return;
        }
        //尝试用token查询用户信息
        Claims claims=null;
        try {
            claims= JwtUtil.parseJWT(token);
        } catch (Exception e) {
            //token超时或非法
            e.printStackTrace();
            ResponseResult result=ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        String userId=claims.getSubject();
        //从redis中拿到完整的当前登录用户
        LoginUser loginUser= redisCache.getCacheObject("WMSLogin:"+userId);

        if(Objects.isNull(loginUser)){
            ResponseResult result=ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(loginUser,null,null);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
