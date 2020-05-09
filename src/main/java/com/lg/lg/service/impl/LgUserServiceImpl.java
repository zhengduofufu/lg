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
    public List<LgUser> selectAllleaderUser() {
        return lgUserMapper.selectAllleaderUser();
    }

    @Override
    public List<LgUser> selectAllByAvaliable() {
        return lgUserMapper.selectAllByAvaliable();
    }

}
