package com.lg.lg.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author admin
 * @date 2020/5/13 9:18
 * 评分进度查看
 */
@Data
public class ProgressUser {
    /**
     * 被评分对象
     */
    private LgUser lgUser;
    /**
     * 完成评分的人
     */
    private List<LgUser> finishiList;
    /**
     * 未完成评分的人
     */
    private List<LgUser> notFinishList;
    /**
     * 完成进度
     */
    private Double proportion;
}
