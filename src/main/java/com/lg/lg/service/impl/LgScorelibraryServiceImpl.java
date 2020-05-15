package com.lg.lg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lg.lg.entity.LgScorelibrary;
import com.lg.lg.mapper.LgScoreLibraryMapper;
import com.lg.lg.service.LgScorelibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author admin
 * @date 2020/5/8 15:29
 */
@Service
@Transactional(rollbackFor={RuntimeException.class, Exception.class})
public class LgScorelibraryServiceImpl extends ServiceImpl<LgScoreLibraryMapper,LgScorelibrary> implements LgScorelibraryService {

    @Autowired
    private LgScoreLibraryMapper lgScoreLibraryMapper;


    @Override
    public List<LgScorelibrary> selectAllScoreLibary() {
        return lgScoreLibraryMapper.selectAllScoreLibary();
    }

    @Override
    public List<LgScorelibrary> selectScoreLibaryByUserIdAndQuarterId(long userId, long quarterId) {
        return lgScoreLibraryMapper.selectScoreLibaryByUserIdAndQuarterId(userId, quarterId);
    }
}
