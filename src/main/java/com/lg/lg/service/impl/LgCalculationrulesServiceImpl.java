package com.lg.lg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lg.lg.entity.LgCalculationrules;
import com.lg.lg.mapper.LgCalculationrulesMapper;
import com.lg.lg.service.LgCalculationrulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author admin
 * @date 2020/5/20 9:49
 */
@Service
@Transactional(rollbackFor={RuntimeException.class, Exception.class})
public class LgCalculationrulesServiceImpl extends ServiceImpl<LgCalculationrulesMapper, LgCalculationrules> implements LgCalculationrulesService {

    @Autowired
    private LgCalculationrulesMapper lgCalculationrulesMapper;

    @Override
    public LgCalculationrules selectByType(long type) {
        return lgCalculationrulesMapper.selectByType(type);
    }
}
