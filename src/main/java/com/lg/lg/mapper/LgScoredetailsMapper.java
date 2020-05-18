package com.lg.lg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lg.lg.entity.LgScoredetails;
import com.lg.lg.entity.LgScorelibrary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author admin
 * @date 2020/5/9 9:28
 */
@Mapper
public interface LgScoredetailsMapper extends BaseMapper<LgScoredetails> {

    /**
     * 查询所有的考核详情
     * @return
     */
    List<LgScoredetails> selectAllScoreDetails();

    /**
     * 删除考核详情数据
     * @return
     */
    Integer deleteUserByQuarterAndUser(@Param("userId") long userId, @Param("quarterId") long quarterId);

    /**
     * 根据季度和用户ID查询所有的考核详情
     * @param userId
     * @param quarterId
     * @return
     */
    List<LgScoredetails> selectUserByQuarterAndUser(@Param("userId") long userId, @Param("quarterId") long quarterId);

    /**
     * 根据用户Id季度Id和领导Id查询所有考核项
     * @param userId
     * @param quarterId
     * @param leaderId
     * @return
     */
    List<LgScoredetails> selectScoreDetialByUserIdAndQuarterIdAndLeaderId(@Param("userId")long userId,
                                                                          @Param("quarterId")long quarterId,  @Param("leaderId")long leaderId);

    /**
     * 通过季度查找所有的考核详情
     * @param quarterId
     * @return
     */
    List<LgScoredetails> selectDetialsUserByQuarter(@Param("quarterId") long quarterId);


    /**
     * 领导通过季度ID和领导id确认提交
     * @param quarterId
     * @param leaderId
     * @return
     */
    List<LgScoredetails> selectScoreDetialByQuarterIdAndLeaderId(@Param("quarterId") long quarterId, @Param("leaderId")long leaderId);
}
