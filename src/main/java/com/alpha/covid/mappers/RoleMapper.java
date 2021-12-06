package com.alpha.covid.mappers;

import com.alpha.covid.models.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role>
{

    List<String> queryRoleNamesByUserId(Long id);

    int updateRoleStatus(Long id, Integer status);
}
