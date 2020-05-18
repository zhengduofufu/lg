package com.lg.lg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lg.lg.entity.LgQuarter;
import com.lg.lg.entity.LgScoredetails;
import com.lg.lg.entity.LgScorelibrary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @date 2020/5/9 9:49
 */
public interface LgScoredetailsService  extends IService<LgScoredetails> {

    /**
     * 查询所有的考核详情
     * @return
     */
    List<LgScoredetails> selectAllScoreDetails();

    /**
     * 删除考核详情数据
     * @return
     */
    Integer deleteUserByQuarterAndUser(long userId, long quarterId);

    /**
     * 根据季度和用户ID查询所有的考核详情
     * @param userId
     * @param quarterId
     * @return
     */
    List<LgScoredetails> selectUserByQuarterAndUser(long userId, long quarterId);

    /**
     * 根据用户Id季度Id和领导Id查询所有考核项
     * @param userId
     * @param quarterId
     * @param leaderId
     * @return
     */
    List<LgScoredetails> selectScoreDetialByUserIdAndQuarterIdAndLeaderId(long userId, long quarterId, long leaderId);

    /**
     * 通过季度查找所有的考核详情
     * @param quarterId
     * @return
     */
    List<LgScoredetails> selectDetialsUserByQuarter( long quarterId);

    /**
     * 领导通过季度ID和领导id确认提交
     * @param quarterId
     * @param leaderId
     * @return
     */
    List<LgScoredetails> selectScoreDetialByQuarterIdAndLeaderId( long quarterId, long leaderId);
}
