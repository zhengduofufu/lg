package com.lg.lg.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("lg_scoresummary")
public class LgScoresummary extends BaseEntity{

  private static final long serialVersionUID = 1L;

  @TableField(value = "userId")
  private long userId;
  @TableField(value = "quarterId")
  private long quarterId;
  @TableField(value = "totalScore")
  private BigDecimal totalScore;
  @TableField(value = "aScore")
  private BigDecimal aScore;
  @TableField(value = "bScore")
  private BigDecimal bScore;
  @TableField(value = "cScore")
  private BigDecimal cScore;
  @TableField(value = "dScore")
  private BigDecimal dScore;
  @TableField(value = "eScore")
  private BigDecimal eScore;

  @TableField(exist = false)
  private String  userName;

  @TableField(exist = false)
  private String  quarterName;
}
