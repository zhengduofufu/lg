package com.lg.lg.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("lg_scoresummary")
public class LgScoresummary extends BaseEntity{

  private static final long serialVersionUID = 1L;

  private long userId;
  private long quarterId;
  private double totalScore;
  private double aScore;
  private double bScore;
  private double cScore;
  private double dScore;
  private double eScore;


}
