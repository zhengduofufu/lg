package com.lg.lg.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lg_role")
public class LgRole extends BaseEntity{

  private static final long serialVersionUID = 1L;

  private String name;
  private long available;

  public LgRole(String name) {
    this.name = name;
  }
}
