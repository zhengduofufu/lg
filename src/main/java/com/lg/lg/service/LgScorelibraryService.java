package com.lg.lg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lg.lg.entity.LgScorelibrary;

import java.util.List;

/**
 * @author admin
 * @date 2020/5/8 15:27
 */
public interface LgScorelibraryService extends IService<LgScorelibrary> {

    /**
     * 查考核库信息
     * @return
     */
    List<LgScorelibrary> selectAllScoreLibary();
}
