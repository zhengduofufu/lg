package com.lg.lg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lg.lg.entity.LgAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Set;

/**
 * @author admin
 * @date 2020/5/26 10:49
 */
@Mapper
public interface LgAuthorityMapper extends BaseMapper<LgAuthority> {

    /**
     * 通过用户Id查权限
     * @param userId
     * @return
     */
    LgAuthority selectByUserId(@Param("userId")long userId);
}
