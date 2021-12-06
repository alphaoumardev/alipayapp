package com.alpha.covid.structor.handler;

import com.alpha.covid.structor.tools.JsonWriterUtil;
import com.alpha.covid.utils.response.FinalResult;
import com.alpha.covid.utils.response.ResultCode;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Primary
@Component
public class SystemAccessDeniedHandler implements AccessDeniedHandler
{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException
    {
        JsonWriterUtil.buildJsonResult(response, FinalResult.error(ResultCode.NOT_AUTH));
    }
}
