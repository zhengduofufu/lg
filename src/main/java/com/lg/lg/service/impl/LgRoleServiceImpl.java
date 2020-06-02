package com.lg.lg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lg.lg.entity.LgRole;
import com.lg.lg.mapper.LgRoleMapper;
import com.lg.lg.service.LgRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author admin
 * @date 2020/5/26 10:59
 */
@Service
@Transactional(rollbackFor={RuntimeException.class, Exception.class})
public class LgRoleServiceImpl extends ServiceImpl<LgRoleMapper, LgRole> implements LgRoleService {


    @Autowired
    private LgRoleMapper lgRoleMapper;

    @Override
    public Set<String> selectByUserId(long userId) {
        return lgRoleMapper.selectByUserId(userId);
    }
}
