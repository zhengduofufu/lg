package com.lg.lg.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("lg_authority")
public class LgAuthority extends BaseEntity{

  private static final long serialVersionUID = 1L;
  private String name;
  private long available;

}
