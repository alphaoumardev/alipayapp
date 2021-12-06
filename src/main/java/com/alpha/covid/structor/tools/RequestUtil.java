package com.alpha.covid.structor.tools;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil
{
    public static String getHeadToken(HttpServletRequest request, String flag)
    {
        return request.getHeader(flag);
    }
}
