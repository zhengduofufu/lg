package com.lg.lg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lg.lg.entity.LgUser;
import org.apache.ibatis.annotations.Param;

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
     * 查询其他部门负责人
     * @param lgUser
     * @return
     */
    List<LgUser> selectOtherDepartmentUser(LgUser lgUser);

    /**
     * 查询其他经营班子
     * @param lgUser
     * @return
     */
    List<LgUser> selectOtherLeaderUser(LgUser lgUser);

    /**
     * 查询所有的经营班子
     * @return
     */
    List<LgUser> selectAllleaderUser();

    /**
     * 查询总经理
     * @return
     */
    LgUser selectManager();

    /**
     * 查询所有未离职员工
     * @return
     */
    List<LgUser> selectAllByAvaliable();

    /**
     * 基层员工评分人
     * @return
     */
    List<LgUser> selectUserJC();

    /**
     * 部门负责人评分人
     * @param lgUser
     * @return
     */
    List<LgUser> selectUserDepart(LgUser lgUser);

    /**
     * 根据季度查询所有的参与考核的人员
     * @return
     */
    List<LgUser> selectAllUserByQuarter(@Param("quarterId") long quarterId);

    /**
     * 根据季度和考核人员查询所有完成考核评分的人员
     * @param quarterId
     * @param userId
     * @return
     */
    List<LgUser> selectFinishUserByQuarterAndUSerId(@Param("quarterId") long quarterId,@Param("userId") long userId);

    /**
     * 根据季度和考核人员查询所有未完成考核评分的人员
     * @param quarterId
     * @param userId
     * @return
     */
    List<LgUser> selectNotFinishUserByQuarterAndUSerId(@Param("quarterId") long quarterId,@Param("userId") long userId);


    /**
     * 根据领导Id和季度Id查询所有的在职员工
     * @return
     */
    List<LgUser> selectAllByAvaliableAndLeaderId(long userId,long queryId);

    /**
     * 根据领导Id和季度Id查询所有的在职员工（已完成未提交）
     * @return
     */
    List<LgUser> selectFinishByAvaliableAndLeaderId(long userId,long queryId);
}
