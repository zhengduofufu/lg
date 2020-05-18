package com.lg.lg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lg.lg.entity.LgUser;
import com.lg.lg.mapper.LgUserMapper;
import com.lg.lg.service.LgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author admin
 * @date 2020/4/28 14:34
 */
@Service
@Transactional(rollbackFor={RuntimeException.class, Exception.class})
public class LgUserServiceImpl extends ServiceImpl<LgUserMapper,LgUser> implements LgUserService {

    @Autowired
    private LgUserMapper lgUserMapper;

    @Override
    public List<LgUser> selectAllUser() {
        return lgUserMapper.selectAllUser();
    }

    @Override
    public List<LgUser> selectAllDepartmentUser() {
        return lgUserMapper.selectAllDepartmentUser();
    }

    @Override
    public List<LgUser> selectOtherDepartmentUser(LgUser lgUser) {
        return lgUserMapper.selectOtherDepartmentUser(lgUser);
    }

    @Override
    public List<LgUser> selectOtherLeaderUser(LgUser lgUser) {
        return lgUserMapper.selectOtherLeaderUser(lgUser);
    }

    @Override
    public List<LgUser> selectAllleaderUser() {
        return lgUserMapper.selectAllleaderUser();
    }

    @Override
    public LgUser selectManager() {
        return lgUserMapper.selectManager();
    }

    @Override
    public List<LgUser> selectAllByAvaliable() {
        return lgUserMapper.selectAllByAvaliable();
    }

    @Override
    public List<LgUser> selectUserJC() {
        return lgUserMapper.selectUserJC();
    }

    @Override
    public List<LgUser> selectUserDepart(LgUser lgUser) {
        return lgUserMapper.selectUserDepart(lgUser);
    }

    @Override
    public List<LgUser> selectAllUserByQuarter(long quarterId) {
        return lgUserMapper.selectAllUserByQuarter(quarterId);
    }

    @Override
    public List<LgUser> selectFinishUserByQuarterAndUSerId(long quarterId, long userId) {
        return lgUserMapper.selectFinishUserByQuarterAndUSerId(quarterId,userId);
    }

    @Override
    public List<LgUser> selectNotFinishUserByQuarterAndUSerId(long quarterId, long userId) {
        return lgUserMapper.selectNotFinishUserByQuarterAndUSerId(quarterId,userId);
    }

    @Override
    public List<LgUser> selectAllByAvaliableAndLeaderId(long userId, long queryId) {
        return lgUserMapper.selectAllByAvaliableAndLeaderId(userId, queryId);
    }

    @Override
    public List<LgUser> selectFinishByAvaliableAndLeaderId(long userId, long queryId) {
        return lgUserMapper.selectFinishByAvaliableAndLeaderId(userId, queryId);
    }

}
