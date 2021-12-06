package com.alpha.covid.structor.handler;

import com.alpha.covid.structor.tools.RequestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Deprecated
public class SystemLogoutHandler implements LogoutHandler
{
    @Value("${token.head}")
    private String head;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    {
        String token = RequestUtil.getHeadToken(request, head);
        if (!token.isEmpty())
        {
            SecurityContextHolder.clearContext();
        }
    }
}
