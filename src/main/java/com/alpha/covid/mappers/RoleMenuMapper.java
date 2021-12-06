package com.alpha.covid.mappers;

import com.alpha.covid.models.Role;
import com.alpha.covid.models.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu>
{
    List<Long> getMenuRoleListById(Role role);
}
