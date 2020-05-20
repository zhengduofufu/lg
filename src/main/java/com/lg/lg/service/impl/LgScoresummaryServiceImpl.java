package com.lg.lg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lg.lg.entity.LgScoresummary;
import com.lg.lg.mapper.LgScoresummaryMapper;
import com.lg.lg.service.LgScoresummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author admin
 * @date 2020/5/20 10:39
 */
@Service
@Transactional(rollbackFor={RuntimeException.class, Exception.class})
public class LgScoresummaryServiceImpl extends ServiceImpl<LgScoresummaryMapper, LgScoresummary> implements LgScoresummaryService {

    @Autowired
    private LgScoresummaryMapper lgScoresummaryMapper;


    @Override
    public Page<LgScoresummary> selectByQuarterId(Page<LgScoresummary> page, QueryWrapper<LgScoresummary> wrapper) {
        return page.setRecords(this.baseMapper.selectByQuarterId(page,wrapper));
    }
}
