package com.lg.lg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lg.lg.entity.LgRole;

import java.util.Set;

/**
 * @author admin
 * @date 2020/5/26 10:55
 */
public interface LgRoleService extends IService<LgRole> {

    /**
     * 通过用户Id角色
     * @param userId
     * @return
     */
    Set<String> selectByUserId(long userId);
}
