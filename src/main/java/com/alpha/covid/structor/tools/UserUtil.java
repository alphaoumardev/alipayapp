package com.alpha.covid.structor.tools;

import com.alpha.covid.beans.vo.UserExportVo;
import com.alpha.covid.beans.vo.UserVo;
import com.alpha.covid.models.User;
import com.alpha.covid.structor.security.LoginUser;
import com.alpha.covid.utils.constant.SysConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UserUtil
{

    public static UserVo executeVo(User user, String deptName)
    {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setForbidden(user.getForbidden() == 0);
        userVo.setSex(user.getSex().equals(SysConstant.MALE) ? "帅哥" : (user.getSex().equals(SysConstant.FEMALE) ? "美女" : "保密"));
        userVo.setDeptName(deptName);
        return userVo;
    }


    public static User executeEntity(UserVo userVo, Long id)
    {
        User user =new User();
        BeanUtils.copyProperties(userVo, user);
        PasswordEncoder passwordEncoder =SpringComponentUtil.getBean("passwordEncoder",PasswordEncoder.class);
        user.setId(id);
        user.setForbidden(SysConstant.ACTIVE);
        user.setSalt(UUID.randomUUID().toString().substring(0,32));
        user.setType(SysConstant.SYSTEM_ACCOUNT);
        if(Objects.isNull(id)&& user.getPassword().isEmpty())
        {
            user.setPassword(passwordEncoder.encode(SysConstant.DEFAULT_PASSWORD));
        }
        return user;
    }

    public static boolean matches(String password, String encodedPwd)
    {
        return SpringComponentUtil.getBean("passwordEncoder", PasswordEncoder.class)
                .matches(password, encodedPwd);
    }

    public static String encode(String password)
    {
        return SpringComponentUtil.getBean("passwordEncoder", PasswordEncoder.class).encode(password);
    }


    public static List<UserExportVo> exportToVo( List<UserVo> userVos)
    {
        List<UserExportVo> userExport= new ArrayList<>(userVos.size());
        userVos.forEach(userVo -> userExport.add(toExport(userVo)));
        return userExport;
    }


    public static UserExportVo toExport(UserVo userVo)
    {
        UserExportVo userExportVo = new UserExportVo();
        BeanUtils.copyProperties(userVo, userExportVo);
        userExportVo.setForbidden(userVo.getForbidden() ? "Black list": "Common user");
        return userExportVo;
    }

    @Nullable
    public static LoginUser getCurrentLoginUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!Objects.isNull(authentication))
        {
            if(authentication instanceof AnonymousAuthenticationToken)
            {
                return null;
            }
            if (authentication instanceof UsernamePasswordAuthenticationToken)
            {
                return (LoginUser) authentication.getPrincipal();
            }
        }
        return null;
    }
}
