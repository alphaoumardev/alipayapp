package com.alpha.covid.mappers;

import com.alpha.covid.beans.vo.LoginReportVo;
import com.alpha.covid.models.LoginLogs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginLogMapper  extends BaseMapper<LoginLogs>
{
    List<LoginReportVo> loginReport(String username);
}
