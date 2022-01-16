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
public class PckUserTransaction extends BaseDomain {

    private Long tranID;
    private String userID;
    private String tranType;
    private String tranAmount;
    private Date tranDate;
    private String tranDetail;

    public Long getTranID() {
        return tranID;
    }

    public void setTranID(Long tranID) {
        this.tranID = tranID;
    }

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

    public String getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(String tranAmount) {
        this.tranAmount = tranAmount;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public String getTranDetail() {
        return tranDetail;
    }

    public void setTranDetail(String tranDetail) {
        this.tranDetail = tranDetail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PckUserTransaction{tranID=").append(tranID);
        sb.append(", userID=").append(userID);
        sb.append(", tranType=").append(tranType);
        sb.append(", tranAmount=").append(tranAmount);
        sb.append(", tranDate=").append(tranDate);
        sb.append(", tranDetail=").append(tranDetail);
        sb.append('}');
        return sb.toString();
    }

}
