package com.University.model;

public class User_Information {
    private Integer userId;
    private Integer informationId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    @Override
    public String toString() {
        return "User_Information{" + "userId=" + userId + ", informationId=" + informationId + '}';
    }

    public User_Information(Integer userId, Integer informationId) {
        this.userId = userId;
        this.informationId = informationId;
    }

    public User_Information() {
    }
}
