/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.model.rest.response;

import java.util.Date;

/**
 *
 * @author tanyong
 */
public class TranDetail {

    private String recNo;
    private String tranType;
    private String tranAmount;
    private Date tranDate;
    private Date tranDetail;

    public String getRecNo() {
        return recNo;
    }

    public void setRecNo(String recNo) {
        this.recNo = recNo;
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

    public Date getTranDetail() {
        return tranDetail;
    }

    public void setTranDetail(Date tranDetail) {
        this.tranDetail = tranDetail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TranDetail{recNo=").append(recNo);
        sb.append(", tranType=").append(tranType);
        sb.append(", tranAmount=").append(tranAmount);
        sb.append(", tranDate=").append(tranDate);
        sb.append(", tranDetail=").append(tranDetail);
        sb.append('}');
        return sb.toString();
    }

}
