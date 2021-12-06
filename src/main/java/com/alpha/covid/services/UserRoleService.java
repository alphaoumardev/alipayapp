package com.alpha.covid.services;

import com.alpha.covid.mappers.UserRoleMapper;
import com.alpha.covid.models.UserRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole>
{
    public List<Long> getRoleIdsByUserId(Long id)
    {
        return this.baseMapper.getRoleIdsByUserId(id);
    }

}
