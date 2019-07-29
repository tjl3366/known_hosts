package com.zb.entity;

import io.swagger.annotations.ApiModelProperty;

public class User {
    @ApiModelProperty(value = "id")
    private  int id;
    @ApiModelProperty(value = "用户名")
    private   String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
