package com.alpha.covid.structor.handler;

import com.alpha.covid.structor.tools.JsonWriterUtil;
import com.alpha.covid.utils.response.FinalResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Deprecated
public class SystemLogoutSuccessHandler implements LogoutSuccessHandler
{
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        JsonWriterUtil.buildJsonResult(httpServletResponse, FinalResult.ok());
    }
}
