package com.lg.lg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lg.lg.entity.LgUser;

import java.util.List;

/**
 * @author admin
 * @date 2020/4/28 14:32
 */
public interface LgUserService extends IService<LgUser> {

    /**
     * 查询所有的用户
     * @return
     */
    List<LgUser> selectAllUser();

    /**
     * 查询所有的部门负责人
     * @return
     */
    List<LgUser> selectAllDepartmentUser();

    /**
     * 查询所有的经营班子
     * @return
     */
    List<LgUser> selectAllleaderUser();

    /**
     * 查询所有未离职员工
     * @return
     */
    List<LgUser> selectAllByAvaliable();
}
