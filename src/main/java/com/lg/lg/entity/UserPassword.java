package com.lg.lg.entity;

import lombok.Data;

/**
 * @author admin
 * @date 2020/5/28 9:34
 */
@Data
public class UserPassword {

    private String oldPass;
    private String newPass;
    private String comfirmPass;
}
