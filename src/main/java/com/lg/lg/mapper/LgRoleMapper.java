package com.lg.lg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lg.lg.entity.LgRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author admin
 * @date 2020/5/26 10:48
 */
@Mapper
public interface LgRoleMapper extends BaseMapper<LgRole> {

    /**
     * 通过用户Id角色
     * @param userId
     * @return
     */
    Set<String> selectByUserId(@Param("userId")long userId);
}
