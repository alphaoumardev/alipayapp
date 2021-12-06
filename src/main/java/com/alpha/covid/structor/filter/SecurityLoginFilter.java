package com.alpha.covid.structor.filter;

import com.alpha.covid.beans.vo.LoginVo;
import com.alpha.covid.config.CustomConfig;
import com.alpha.covid.services.CustomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

public class SecurityLoginFilter extends UsernamePasswordAuthenticationFilter
{
    private final CustomService customService;

    public SecurityLoginFilter(CustomService customService)
    {
        this.customService = customService;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/user/login", "POST"));
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication( HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
    {
        if (!request.getMethod().equals("POST"))
        {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = request.getInputStream();
        LoginVo loginBody = objectMapper.readValue(inputStream, LoginVo.class);

        if (loginBody.getUsername().isEmpty()|| loginBody.getPassword().isEmpty())
        {
            throw new InternalAuthenticationServiceException("The username and the password can't be empty！");
        }

        if (loginBody.getVerifyCode().isEmpty())
        {
            throw new AuthenticationServiceException("Please input the password！");
        }
        // The verification code
        Long interval = customService.getKeyInterval(CustomConfig.LOGIN_VERIFY_CODE + loginBody.getVerifyCodeId());
        if (interval==null || interval <= 3)
        {
            throw new AuthenticationServiceException("The token has expired ！");
        }

        String code = customService.getLoginVerifyCode(loginBody.getVerifyCodeId());
        if (!loginBody.getVerifyCode().equalsIgnoreCase(code))
        {
            throw new AuthenticationServiceException("The token is incorrect！");
        }
        customService.deleteLoginVerifyCode(loginBody.getVerifyCodeId());

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword());
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
