package com.lg.lg.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("lg_scoredetails")
public class LgScoredetails extends BaseEntity {

  @TableField(value = "userId")
  private long userId;
  @TableField(value = "scoreId")
  private long scoreId;
  @TableField(value = "taterId")
  private long taterId;
  @TableField(value = "quarterId")
  private long quarterId;
  private long status;
  @TableField(value = "amisAmount")
  private BigDecimal amisAmount;
  @TableField(value = "finishedAmount")
  private BigDecimal finishedAmount;
  private BigDecimal score;
  @TableField(value = "comfirmTime")
  private LocalDateTime comfirmTime;
  //权重
  private BigDecimal weights;

  @TableField(exist = false)
  private String scoreName;

  @TableField(exist = false)
  private long type;
}
