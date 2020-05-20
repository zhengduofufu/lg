package com.lg.lg.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lg.lg.entity.LgScoresummary;

import java.util.List;

/**
 * @author admin
 * @date 2020/5/20 10:38
 */
public interface LgScoresummaryService extends IService<LgScoresummary> {

    /**
     * 根据季度Id查询所有的考核汇总信息
     * @param page
     * @return
     */
    Page<LgScoresummary> selectByQuarterId(Page<LgScoresummary> page, QueryWrapper<LgScoresummary> wrapper);
}
