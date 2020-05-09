package com.lg.lg.entity;

import lombok.Data;

/**
 * @author admin
 * @date 2020/5/8 14:11
 */
@Data
public class UserType {
    String id;

    String name;

    public UserType(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
