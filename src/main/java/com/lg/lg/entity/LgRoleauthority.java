package com.lg.lg.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("lg_roleauthority")
public class LgRoleauthority extends BaseEntity{

  private static final long serialVersionUID = 1L;

  private long authorityId;
  private long roleId;


}
