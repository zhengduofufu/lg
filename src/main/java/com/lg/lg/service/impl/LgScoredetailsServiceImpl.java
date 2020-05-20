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

import java.math.BigDecimal;
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

    @Override
    public Integer deleteUserByQuarterAndUser(long userId, long quarterId) {
        return lgScoredetailsMapper.deleteUserByQuarterAndUser(userId,quarterId);
    }

    @Override
    public List<LgScoredetails> selectUserByQuarterAndUser(long userId, long quarterId) {
        return lgScoredetailsMapper.selectUserByQuarterAndUser(userId,quarterId);
    }

    @Override
    public List<LgScoredetails> selectScoreDetialByUserIdAndQuarterIdAndLeaderId(long userId, long quarterId, long leaderId) {
        return lgScoredetailsMapper.selectScoreDetialByUserIdAndQuarterIdAndLeaderId(userId,quarterId,leaderId);
    }

    @Override
    public List<LgScoredetails> selectDetialsUserByQuarter(long quarterId) {
        return lgScoredetailsMapper.selectDetialsUserByQuarter(quarterId);
    }

    @Override
    public List<LgScoredetails> selectScoreDetialByQuarterIdAndLeaderId(long quarterId, long leaderId) {
        return lgScoredetailsMapper.selectScoreDetialByQuarterIdAndLeaderId(quarterId, leaderId);
    }

    @Override
    public BigDecimal selectScoreSumAByQuarterIdAndUserId(long quarterId, long userId) {
        return lgScoredetailsMapper.selectScoreSumAByQuarterIdAndUserId(quarterId,  userId);
    }

    @Override
    public BigDecimal selectScoreSumBByQuarterIdAndUserId(long quarterId, long userId) {
        return lgScoredetailsMapper.selectScoreSumBByQuarterIdAndUserId(quarterId,  userId);
    }

    @Override
    public BigDecimal selectScoreSumCByQuarterIdAndUserId(long quarterId, long userId) {
        return lgScoredetailsMapper.selectScoreSumCByQuarterIdAndUserId(quarterId,  userId);
    }

    @Override
    public BigDecimal selectScoreSumDByQuarterIdAndUserId(long quarterId, long userId) {
        return lgScoredetailsMapper.selectScoreSumDByQuarterIdAndUserId(quarterId,  userId);
    }

    @Override
    public BigDecimal selectScoreSumEByQuarterIdAndUserId(long quarterId, long userId) {
        return lgScoredetailsMapper.selectScoreSumEByQuarterIdAndUserId(quarterId,  userId);
    }
}
