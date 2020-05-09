package com.lg.lg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lg.lg.entity.LgQuarter;
import com.lg.lg.entity.LgUser;

import java.util.List;

/**
 * @author admin
 * @date 2020/5/8 16:31
 */
public interface LgQuarterService extends IService<LgQuarter> {

    /**
     * 查询所有的季度考核信息
     * @return
     */
    List<LgQuarter> selectAllQuarter();

    /**
     * 查询季度是否重复
     * @return
     */
    List<LgQuarter> selectByYearAndQuarter(LgQuarter lgQuarter);
}
