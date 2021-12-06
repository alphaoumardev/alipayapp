package com.alpha.covid.structor.handler;

import com.alpha.covid.structor.tools.JsonWriterUtil;
import com.alpha.covid.utils.response.FinalResult;
import com.alpha.covid.utils.response.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SystemAuthEntryPoint implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException
    {
        JsonWriterUtil.buildJsonResult(response, FinalResult.error(ResultCode.USER_NOT_LOGIN));
    }
}
