package com.lg.lg.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("lg_user")
public class LgUser extends BaseEntity{

  private static final long serialVersionUID = 1L;

  @TableField(value = "userName")
  private String userName;
  private String password;
  private String tel;
  private long avaliable;
  private String type;
  @TableField(value = "departmentId")
  private long departmentId;
  @TableField(value = "leaderId")
  private long leaderId;
  @TableField(value = "roleId")
  private long roleId;
  private String depart;
  @TableField(exist = false)
  private long color;

}
