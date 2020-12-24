package com.University.model;

import javax.persistence.*;
import java.util.Date;

public class Information {
    private Integer informationId;
    private Integer informationType;
    private String informationTitle;
    private String informationContent;
    private Double informationMoney;
    private Integer informationStatus;
    private Integer informationUser;
    private String informationPlace;
    private Date informationStarttime;
    private String informationLatitude;
    private String informationLongitude;
    private String informationEndtime;
    private String informationTown;
    private String informationDetailocation;
    private Integer informationLimitSex;
    private Integer informationPeopleNum;
    private String informationImages;
    private User user;

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public Integer getInformationType() {
        return informationType;
    }

    public void setInformationType(Integer informationType) {
        this.informationType = informationType;
    }

    public String getInformationTitle() {
        return informationTitle;
    }

    public void setInformationTitle(String informationTitle) {
        this.informationTitle = informationTitle;
    }

    public String getInformationContent() {
        return informationContent;
    }

    public void setInformationContent(String informationContent) {
        this.informationContent = informationContent;
    }

    public Double getInformationMoney() {
        return informationMoney;
    }

    public void setInformationMoney(Double informationMoney) {
        this.informationMoney = informationMoney;
    }

    public Integer getInformationStatus() {
        return informationStatus;
    }

    public void setInformationStatus(Integer informationStatus) {
        this.informationStatus = informationStatus;
    }

    public Integer getInformationUser() {
        return informationUser;
    }

    public void setInformationUser(Integer informationUser) {
        this.informationUser = informationUser;
    }

    public String getInformationPlace() {
        return informationPlace;
    }

    public void setInformationPlace(String informationPlace) {
        this.informationPlace = informationPlace;
    }

    public Date getInformationStarttime() {
        return informationStarttime;
    }

    public void setInformationStarttime(Date informationStarttime) {
        this.informationStarttime = informationStarttime;
    }

    public String getInformationLatitude() {
        return informationLatitude;
    }

    public void setInformationLatitude(String informationLatitude) {
        this.informationLatitude = informationLatitude;
    }

    public String getInformationLongitude() {
        return informationLongitude;
    }

    public void setInformationLongitude(String informationLongitude) {
        this.informationLongitude = informationLongitude;
    }

    public String getInformationEndtime() {
        return informationEndtime;
    }

    public void setInformationEndtime(String informationEndtime) {
        this.informationEndtime = informationEndtime;
    }

    public String getInformationTown() {
        return informationTown;
    }

    public void setInformationTown(String informationTown) {
        this.informationTown = informationTown;
    }

    public String getInformationDetailocation() {
        return informationDetailocation;
    }

    public void setInformationDetailocation(String informationDetailocation) {
        this.informationDetailocation = informationDetailocation;
    }

    public Integer getInformationLimitSex() {
        return informationLimitSex;
    }

    public void setInformationLimitSex(Integer informationLimitSex) {
        this.informationLimitSex = informationLimitSex;
    }

    public Integer getInformationPeopleNum() {
        return informationPeopleNum;
    }

    public void setInformationPeopleNum(Integer informationPeopleNum) {
        this.informationPeopleNum = informationPeopleNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getInformationImages() {
        return informationImages;
    }

    public void setInformationImages(String informationImages) {
        this.informationImages = informationImages;
    }

    public Information(Integer informationId, Integer informationType, String informationTitle, String informationContent, Double informationMoney, Integer informationStatus, Integer informationUser, String informationPlace, Date informationStarttime, String informationLatitude, String informationLongitude, String informationEndtime, String informationTown, String informationDetailocation, Integer informationLimitSex, Integer informationPeopleNum, String informationImages, User user) {
        this.informationId = informationId;
        this.informationType = informationType;
        this.informationTitle = informationTitle;
        this.informationContent = informationContent;
        this.informationMoney = informationMoney;
        this.informationStatus = informationStatus;
        this.informationUser = informationUser;
        this.informationPlace = informationPlace;
        this.informationStarttime = informationStarttime;
        this.informationLatitude = informationLatitude;
        this.informationLongitude = informationLongitude;
        this.informationEndtime = informationEndtime;
        this.informationTown = informationTown;
        this.informationDetailocation = informationDetailocation;
        this.informationLimitSex = informationLimitSex;
        this.informationPeopleNum = informationPeopleNum;
        this.informationImages = informationImages;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Information{" + "informationId=" + informationId + ", informationType=" + informationType + ", informationTitle='" + informationTitle + '\'' + ", informationContent='" + informationContent + '\'' + ", informationMoney=" + informationMoney + ", informationStatus=" + informationStatus + ", informationUser=" + informationUser + ", informationPlace='" + informationPlace + '\'' + ", informationStarttime=" + informationStarttime + ", informationLatitude='" + informationLatitude + '\'' + ", informationLongitude='" + informationLongitude + '\'' + ", informationEndtime='" + informationEndtime + '\'' + ", informationTown='" + informationTown + '\'' + ", informationDetailocation='" + informationDetailocation + '\'' + ", informationLimitSex=" + informationLimitSex + ", informationPeopleNum=" + informationPeopleNum + ", informationImages='" + informationImages + '\'' + ", user=" + user + '}';
    }

    public Information() {
    }
}
