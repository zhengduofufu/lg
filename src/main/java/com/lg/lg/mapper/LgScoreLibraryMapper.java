package com.lg.lg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lg.lg.entity.LgScorelibrary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author admin
 * @date 2020/5/8 15:22
 */
@Mapper
public interface LgScoreLibraryMapper extends BaseMapper<LgScorelibrary> {

    List<LgScorelibrary> selectAllScoreLibary();

    List<LgScorelibrary> selectScoreLibaryByUserIdAndQuarterId(@Param("userId") long userId, @Param("quarterId") long quarterId);
}
