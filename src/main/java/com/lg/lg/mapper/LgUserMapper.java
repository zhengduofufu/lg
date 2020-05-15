package com.lg.lg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lg.lg.entity.LgUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author admin
 * @date 2020/4/28 14:16
 */
@Mapper
public interface LgUserMapper extends BaseMapper<LgUser> {

    List<LgUser> selectAllUser();

    List<LgUser> selectAllDepartmentUser();

    List<LgUser> selectAllleaderUser();

    List<LgUser> selectAllByAvaliable();
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
     * 总经理
     * @return
     */
    LgUser selectManager();

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
}
