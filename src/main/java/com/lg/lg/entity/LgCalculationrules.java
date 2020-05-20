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
@TableName("lg_calculationrules")
public class LgCalculationrules extends BaseEntity{

  private static final long serialVersionUID = 1L;

  private BigDecimal aweights;
  private BigDecimal bweights;
  private BigDecimal cweights;
  private BigDecimal dweights;
  private BigDecimal eweights;
  private long type;


}
