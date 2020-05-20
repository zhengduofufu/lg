package com.lg.lg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lg.lg.entity.LgCalculationrules;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author admin
 * @date 2020/5/20 9:44
 */
@Mapper
public interface LgCalculationrulesMapper extends BaseMapper<LgCalculationrules> {

    /**
     * 根据类型查找规则
     * @param type
     * @return
     */
     LgCalculationrules selectByType(@Param("type") long type);

}
