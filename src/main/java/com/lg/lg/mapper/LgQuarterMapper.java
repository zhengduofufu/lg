package com.lg.lg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lg.lg.entity.LgQuarter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author admin
 * @date 2020/5/8 16:24
 */
@Mapper
public interface LgQuarterMapper extends BaseMapper<LgQuarter> {
    List<LgQuarter> selectAllQuarter();

    List<LgQuarter> selectByYearAndQuarter(LgQuarter lgQuarter);

    List<LgQuarter> selectByUserIdAndLeaderId(@Param("userId") long userId,@Param("quarterId") long quarterId, @Param("leaderId") long leaderId);
}
