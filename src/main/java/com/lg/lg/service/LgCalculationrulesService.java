package com.lg.lg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lg.lg.entity.LgCalculationrules;
import org.apache.ibatis.annotations.Param;

/**
 * @author admin
 * @date 2020/5/20 9:48
 */
public interface LgCalculationrulesService extends IService<LgCalculationrules> {
    /**
     * 根据类型查找规则
     * @param type
     * @return
     */
    LgCalculationrules selectByType(@Param("type") long type);
}
