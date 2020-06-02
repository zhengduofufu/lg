package com.lg.lg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lg.lg.entity.LgAuthority;

import java.util.Set;

/**
 * @author admin
 * @date 2020/5/26 10:54
 */
public interface LgAuthorityService extends IService<LgAuthority> {

    /**
     * 通过用户Id查权限
     * @param userId
     * @return
     */
    LgAuthority selectByUserId(long userId);
}
