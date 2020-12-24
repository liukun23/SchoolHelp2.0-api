package com.University.entity;

/**
 * 载荷对象
 */
public class UserInfo {

    private Integer id;
    private String openid;
    private String session_key;
    private String username;

    public UserInfo() {
    }

    public UserInfo(String openid, String session_key) {
        this.openid = openid;
        this.session_key = session_key;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public UserInfo(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}