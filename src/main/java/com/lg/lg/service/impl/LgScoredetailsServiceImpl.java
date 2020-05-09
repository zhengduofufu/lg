package com.lg.lg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lg.lg.entity.LgQuarter;
import com.lg.lg.entity.LgScoredetails;
import com.lg.lg.mapper.LgQuarterMapper;
import com.lg.lg.mapper.LgScoredetailsMapper;
import com.lg.lg.service.LgQuarterService;
import com.lg.lg.service.LgScoredetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author admin
 * @date 2020/5/9 9:51
 */
@Service
@Transactional(rollbackFor={RuntimeException.class, Exception.class})
public class LgScoredetailsServiceImpl extends ServiceImpl<LgScoredetailsMapper, LgScoredetails> implements LgScoredetailsService {

    @Autowired
    private LgScoredetailsMapper lgScoredetailsMapper;


    @Override
    public List<LgScoredetails> selectAllScoreDetails() {
        return lgScoredetailsMapper.selectAllScoreDetails();
    }
}
