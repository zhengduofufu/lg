package com.lg.lg.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lg.lg.entity.LgScoresummary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author admin
 * @date 2020/5/20 10:34
 */
@Mapper
public interface LgScoresummaryMapper extends BaseMapper<LgScoresummary> {

    /**
     * 根据季度Id查询所有的考核汇总信息
     * @param page
     * @return
     */
    List<LgScoresummary> selectByQuarterId(Page page, @Param(Constants.WRAPPER) QueryWrapper<LgScoresummary> wrapper);
}
