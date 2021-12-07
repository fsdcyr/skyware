package com.fsdcyr.sky.authorization.filter;

import com.fsdcyr.sky.authorization.context.AuthThreadContext;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-22 5:48 下午
 */
public class AuthContextHttpFilter extends OncePerRequestFilter {

    /**
     * 避免线程复用，请求完成结束清空现场
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 进入前清理现场
        AuthThreadContext.unbind();

        filterChain.doFilter(httpServletRequest, httpServletResponse);

        // 完成后清理现场
        AuthThreadContext.unbind();
    }

}
