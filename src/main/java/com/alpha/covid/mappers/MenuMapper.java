package com.alpha.covid.mappers;

import com.alpha.covid.models.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu>
{
    Integer checkHasChild(Long id);

    void updateChildren(List<Long> ids, Integer newStatus);
}
