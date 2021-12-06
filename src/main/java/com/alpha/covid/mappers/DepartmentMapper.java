package com.alpha.covid.mappers;

import com.alpha.covid.beans.vo.DepartmentVo;
import com.alpha.covid.models.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department>
{
    List<Department> findandcount();

    List<DepartmentVo> getDepartcount();

    Long getDepartIdByName(String name);

    String getDepartmentNameById(Long departmentId);

    List<String> selectDepartmentNamesByIds(@Param("ids") List<Long> deptIds);

    Integer getDepartParent(Long id);
}
