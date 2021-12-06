package com.alpha.covid.services;

import com.alpha.covid.mappers.RoleMapper;
import com.alpha.covid.mappers.RoleMenuMapper;
import com.alpha.covid.models.Role;
import com.alpha.covid.models.RoleMenu;
import com.alpha.covid.utils.exception.BusinessException;
import com.alpha.covid.utils.response.ResultCode;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenu>
{
    private final RoleMapper roleMapper;

    public List<Long> getRoleMenuById(Long id)
    {
        Role role = roleMapper.selectById(id);
        if(Objects.isNull(role))
        {
            throw new BusinessException(ResultCode.ROLE_NOT_EXIST);
        }
        return this.baseMapper.getMenuRoleListById(role);
    }
}
