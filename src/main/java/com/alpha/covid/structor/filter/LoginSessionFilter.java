package com.alpha.covid.structor.filter;

import com.alpha.covid.structor.security.LoginUser;
import com.alpha.covid.structor.tools.RequestUtil;
import com.alpha.covid.structor.tools.UserUtil;
import com.alpha.covid.utils.exception.BusinessException;
import com.alpha.covid.utils.response.ResultCode;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

//@Deprecated
public class LoginSessionFilter implements Filter
 {

    @Value("${token.head}")
    private String head;

     @Override
     public void init(FilterConfig filterConfig) throws ServletException {}

     @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // Get the token request
        String token = RequestUtil.getHeadToken((HttpServletRequest) servletRequest, head);
        if (token !=null)
        {
            LoginUser loginUser = UserUtil.getCurrentLoginUser();
            if (!Objects.isNull(loginUser)){
                long now = System.currentTimeMillis();
                if (now > loginUser.getExpireTime())
                {
                    throw new BusinessException(ResultCode.USER_SESSION_INVALID);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

//     @Override
//     public void destroy() {}
 }
