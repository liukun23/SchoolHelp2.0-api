package com.University.entity;

public class RequestStatus {

    private Integer Status;
    private String msg;

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RequestStatus{" + "Status=" + Status + ", msg='" + msg + '\'' + '}';
    }

    public RequestStatus() {
    }
}
