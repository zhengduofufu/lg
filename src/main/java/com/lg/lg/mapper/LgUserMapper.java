package com.lg.lg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lg.lg.entity.LgUser;
import org.apache.ibatis.annotations.Mapper;

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
}
