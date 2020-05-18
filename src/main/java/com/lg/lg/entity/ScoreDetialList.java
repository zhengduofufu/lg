package com.lg.lg.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author admin
 * @date 2020/5/12 12:52
 */
@Data
public class ScoreDetialList {
    private List<BigDecimal> amisAmount;
    private List<BigDecimal> finishedAmount;
    private List<BigDecimal> weights;
    private List<BigDecimal> scoreId;
    private List<BigDecimal> score;
    private List<Long> id;
}
