package com.alpha.covid.structor.security;

import com.alpha.covid.models.Menu;
import com.alpha.covid.models.User;
import com.alpha.covid.utils.constant.SysConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoginUser extends User implements UserDetails
{
    @Serial
    private static final long serialVersionUID = -1379274258881257107L;
    private List<String> authorities;
    private List<Menu> menus;
    private String token;
    private Long loginTime;
    private Long expireTime;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities.parallelStream().filter(auth -> !auth.isEmpty())
                .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return SysConstant.ACTIVE.equals(getForbidden());
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return SysConstant.ACTIVE.equals(getForbidden());
    }
}
