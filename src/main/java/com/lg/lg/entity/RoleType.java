package com.lg.lg.entity;

import lombok.Data;

/**
 * @author admin
 * @date 2020/5/8 14:33
 */
@Data
public class RoleType {
    long id;
    String name;

    public RoleType(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
