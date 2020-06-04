package com.lg.lg.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author admin
 * @date 2020/5/18 10:43
 */
@Data
public class QuarterAndSocreDetial {
    private LgQuarter lgQuarter;
    private List<LgScoredetails> lgScoredetailsList;
    private long totalNum;

}
