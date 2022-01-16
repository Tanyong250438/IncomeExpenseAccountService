/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.domain;

import java.util.Date;

/**
 *
 * @author tanyong
 */
public class PckUser extends BaseDomain {

    private String userID;
    private String username;
    private String userEmail;
    private String jack;
    private String firstName;
    private String lastName;
    private String picture;
    private String status;
    private String acountNonLocked;
    private String authorize;
    private Date updateJackDate;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getJack() {
        return jack;
    }

    public void setJack(String jack) {
        this.jack = jack;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAcountNonLocked() {
        return acountNonLocked;
    }

    public void setAcountNonLocked(String acountNonLocked) {
        this.acountNonLocked = acountNonLocked;
    }

    public String getAuthorize() {
        return authorize;
    }

    public void setAuthorize(String authorize) {
        this.authorize = authorize;
    }

    public Date getUpdateJackDate() {
        return updateJackDate;
    }

    public void setUpdateJackDate(Date updateJackDate) {
        this.updateJackDate = updateJackDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PckUser{userID=").append(userID);
        sb.append(", username=").append(username);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", jack=").append(jack);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", picture=").append(picture);
        sb.append(", status=").append(status);
        sb.append(", acountNonLocked=").append(acountNonLocked);
        sb.append(", authorize=").append(authorize);
        sb.append(", updateJackDate=").append(updateJackDate);
        sb.append('}');
        return sb.toString();
    }

}
