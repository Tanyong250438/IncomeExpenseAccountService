/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author tanyong
 */
public class UserAuthentication extends BaseDomain implements UserDetails {

    private String userID;
    private String username;
    private String userEmail;
    private String jack;
    private List<GrantedAuthority> authorizeList;

    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private String isDelete;

    private String firstName;
    private String lastName;
    private String picture;

    private Date updateJackDate;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
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

    @Override
    public String getPassword() {
        return jack;
    }

    public void setPassword(String jack) {
        this.jack = jack;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorizeList;
    }

    public void setAuthorizeList(List<GrantedAuthority> authorizeList) {
        this.authorizeList = authorizeList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setIsAccountNonLocked(boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setIsCredentialsNonExpired(boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
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

    public Date getUpdateJackDate() {
        return updateJackDate;
    }

    public void setUpdateJackDate(Date updateJackDate) {
        this.updateJackDate = updateJackDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserAuthentication{userID=").append(userID);
        sb.append(", username=").append(username);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", jack=").append(jack);
        sb.append(", authorizeList=").append(authorizeList);
        sb.append(", isAccountNonLocked=").append(isAccountNonLocked);
        sb.append(", isCredentialsNonExpired=").append(isCredentialsNonExpired);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", picture=").append(picture);
        sb.append(", updateJackDate=").append(updateJackDate);
        sb.append('}');
        return sb.toString();
    }

}
