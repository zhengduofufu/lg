package com.lg.lg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lg.lg.entity.LgQuarter;
import com.lg.lg.entity.LgUser;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据用户ID和领导ID查询所有的除当前季度以外的季度
     * @param userId
     * @param leaderId
     * @return
     */
    List<LgQuarter> selectByUserIdAndLeaderId(long userId, long quarterId,long leaderId);

    /**
     * 查询除当前季度以外的季度
     * @param quarterId
     * @return
     */
    List<LgQuarter> selectByQuarterId(long quarterId);
}
