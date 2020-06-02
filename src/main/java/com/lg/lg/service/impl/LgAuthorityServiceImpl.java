package com.lg.lg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lg.lg.entity.LgAuthority;
import com.lg.lg.mapper.LgAuthorityMapper;
import com.lg.lg.service.LgAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author admin
 * @date 2020/5/26 10:56
 */
@Service
@Transactional(rollbackFor={RuntimeException.class, Exception.class})
public class LgAuthorityServiceImpl extends ServiceImpl<LgAuthorityMapper, LgAuthority> implements LgAuthorityService {


    @Autowired
    private LgAuthorityMapper lgAuthorityMapper;

    @Override
    public LgAuthority selectByUserId(long userId) {
        return lgAuthorityMapper.selectByUserId(userId);
    }
}
