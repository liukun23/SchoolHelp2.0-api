package com.University.model;

public class UserCode {
    private Integer userId;
    private String userName;
    private String nickname;
    private String avatarurl;
    private Integer sex;
    private String city;
    private String phone;
    private Integer integration;
    private Integer roleId;
    private Integer hobbyHelpId;
    private String openId;
    private String unionId;
    private String code;

    public UserCode(Integer userId, String userName, String nickname, String avatarurl, Integer sex, String city, String phone, Integer integration, Integer roleId, Integer hobbyHelpId, String openId, String unionId, String code) {
        this.userId = userId;
        this.userName = userName;
        this.nickname = nickname;
        this.avatarurl = avatarurl;
        this.sex = sex;
        this.city = city;
        this.phone = phone;
        this.integration = integration;
        this.roleId = roleId;
        this.hobbyHelpId = hobbyHelpId;
        this.openId = openId;
        this.unionId = unionId;
        this.code = code;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getHobbyHelpId() {
        return hobbyHelpId;
    }

    public void setHobbyHelpId(Integer hobbyHelpId) {
        this.hobbyHelpId = hobbyHelpId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "UserCode{" + "userId=" + userId + ", userName='" + userName + '\'' + ", nickname='" + nickname + '\'' + ", avatarurl='" + avatarurl + '\'' + ", sex=" + sex + ", city='" + city + '\'' + ", phone='" + phone + '\'' + ", integration=" + integration + ", roleId=" + roleId + ", hobbyHelpId=" + hobbyHelpId + ", openId='" + openId + '\'' + ", unionId='" + unionId + '\'' + ", code='" + code + '\'' + '}';
    }

    public UserCode() {
    }
}
