package com.lg.lg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lg.lg.entity.LgQuarter;
import com.lg.lg.entity.LgUser;
import com.lg.lg.mapper.LgQuarterMapper;
import com.lg.lg.mapper.LgUserMapper;
import com.lg.lg.service.LgQuarterService;
import com.lg.lg.service.LgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author admin
 * @date 2020/5/8 16:33
 */
@Service
@Transactional(rollbackFor={RuntimeException.class, Exception.class})
public class LgQuarterServiceImpl extends ServiceImpl<LgQuarterMapper, LgQuarter> implements LgQuarterService {

    @Autowired
    private LgQuarterMapper lgQuarterMapper;

    @Override
    public List<LgQuarter> selectAllQuarter() {
        return lgQuarterMapper.selectAllQuarter();
    }

    @Override
    public List<LgQuarter> selectByYearAndQuarter(LgQuarter lgQuarter) {
        return lgQuarterMapper.selectByYearAndQuarter(lgQuarter);
    }

    @Override
    public List<LgQuarter> selectByUserIdAndLeaderId(long userId, long quarterId,long leaderId) {
        return lgQuarterMapper.selectByUserIdAndLeaderId(userId, quarterId,leaderId);
    }

    @Override
    public List<LgQuarter> selectByQuarterId(long quarterId) {
        return lgQuarterMapper.selectByQuarterId(quarterId);
    }
}
