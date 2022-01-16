/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.model.rest.request;

import java.util.Date;

/**
 *
 * @author tanyong
 */
public class GetUserDailyReportRequest {

    private String userID;
    private String tranType;
    private Date tranDate;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GetUserDailyReportRequest{userID=").append(userID);
        sb.append(", tranType=").append(tranType);
        sb.append(", tranDate=").append(tranDate);
        sb.append('}');
        return sb.toString();
    }

}
