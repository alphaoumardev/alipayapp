package com.alpha.covid.structor.security;

import com.alpha.covid.mappers.RoleMapper;
import com.alpha.covid.mappers.UserMapper;
import com.alpha.covid.mappers.UserRoleMapper;
import com.alpha.covid.models.Menu;
import com.alpha.covid.models.Role;
import com.alpha.covid.models.User;
import com.alpha.covid.structor.tools.JsonWriterUtil;
import com.alpha.covid.utils.constant.SysConstant;
import com.alpha.covid.utils.response.FinalResult;
import com.alpha.covid.utils.response.ResultCode;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("userDetailsServices")
@AllArgsConstructor
public class SysUserDetailsServices implements UserDetailsService
{
    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final RoleMapper roleMapper;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String strs)
    {
        User user = userMapper.getByUsername(strs);
        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();

        if(!Objects.isNull(user))
        {
            assert response != null;
            JsonWriterUtil.buildJsonResult(response, FinalResult.error(ResultCode.USER_ACCOUNT_NOT_EXIST));
            return null;
        }

        assert false;
        List<Long> roleIds= userRoleMapper.getRoleIdsByUserId(user.getId());
        if(roleIds.isEmpty())
        {
            JsonWriterUtil.buildJsonResult(response, FinalResult.error(ResultCode.DONT_HAVE_ANY_ROLE));
            return null;
        }
        List<Role> roles = roleMapper.selectBatchIds(roleIds);
        List<String> codes = roles.stream().map(Role::getRoleCode).collect(Collectors.toList());
        if(!codes.contains("ADMIN"))
        {
            List<Integer> flags = roles.stream().map(Role::getForbidden).collect(Collectors.toList());
            if(!flags.contains(SysConstant.ACTIVE))
            {
                JsonWriterUtil.buildJsonResult(response, FinalResult.error(ResultCode.ROLE_ALL_IS_FORBIDDEN));
                return null;
            }
        }
        LoginUser loginUser =new LoginUser();
        BeanUtils.copyProperties(user, loginUser);

        List<String> codeList = userMapper.getAuthListByUsername(user.getUsername(), SysConstant.ADMIN.equals(user.getType()));
        List<Menu> menus = userMapper.getMenusByUsername(Integer.parseInt(SysConstant.MENU), user.getUsername());

        loginUser.setAuthorities(codeList);
        loginUser.setMenus(menus);

        return loginUser;
    }
}
