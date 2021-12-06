package com.alpha.covid.services;

import com.alpha.covid.config.CustomConfig;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class CustomService
{
    public final StringRedisTemplate stringRedisTemplete;

    public String getLoginVerifyCode(String verifyCodeId)
    {
        return stringRedisTemplete.opsForValue().get(CustomConfig.LOGIN_VERIFY_CODE + verifyCodeId);
    }

    public void setLoginVerifyCode(String uuid, String verifyCode, long loginVerifyCode)
    {
        String key= CustomConfig.LOGIN_VERIFY_CODE+uuid;
        if(isExitKey(key))
        {
            removeByKey(key);
        }
        stringRedisTemplete.opsForValue().set(key,  verifyCode, loginVerifyCode, TimeUnit.SECONDS);
    }
    public void removeByKey(String key)
    {
        stringRedisTemplete.delete(key);
    }
    public boolean isExitKey(String key)
    {
        Boolean bool = stringRedisTemplete.hasKey(key);
        return Objects.equals(bool,true);
    }

    public void deleteLoginVerifyCode(String verifyCodeId)
    {
        String key = CustomConfig.LOGIN_VERIFY_CODE + verifyCodeId;
        stringRedisTemplete.delete(key);
    }

    public Long getKeyInterval(String loginVerifyCode)
    {
        return stringRedisTemplete.getExpire(loginVerifyCode);
    }

}
