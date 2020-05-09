package com.lg.lg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lg.lg.entity.LgQuarter;
import com.lg.lg.entity.LgScoredetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @date 2020/5/9 9:49
 */
public interface LgScoredetailsService  extends IService<LgScoredetails> {

    /**
     * 查询所有的考核详情
     * @return
     */
    List<LgScoredetails> selectAllScoreDetails();
}
