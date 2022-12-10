package com.breed.govern.mapper;

import com.breed.govern.dto.vo.ExcInfoDisease;
import com.breed.govern.entity.BExcSummary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 问题解决汇总表 Mapper 接口
 * </p>
 *
 * @author 王进
 * @since 2022-12-10
 */
public interface BExcSummaryMapper extends BaseMapper<BExcSummary> {

    ExcInfoDisease getSummaryByLabel(@Param(value = "data")String data);
}
