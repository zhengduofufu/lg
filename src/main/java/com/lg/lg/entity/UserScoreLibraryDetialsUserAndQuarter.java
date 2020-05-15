package com.lg.lg.entity;

import lombok.Data;

import java.util.List;

/**
 * @author admin
 * @date 2020/5/13 15:08
 */
@Data
public class UserScoreLibraryDetialsUserAndQuarter {
    private List<UserScoreLibraryDetials> userScoreLibraryDetials;
    private long userId;
    private long quarterId;
}
