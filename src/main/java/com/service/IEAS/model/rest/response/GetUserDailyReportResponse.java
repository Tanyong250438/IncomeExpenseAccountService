/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.model.rest.response;

import java.util.List;

/**
 *
 * @author tanyong
 */
public class GetUserDailyReportResponse {

    private List<TranDetail> tranDetailList;

    public List<TranDetail> getTranDetailList() {
        return tranDetailList;
    }

    public void setTranDetailList(List<TranDetail> tranDetailList) {
        this.tranDetailList = tranDetailList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GetUserDailyReportResponse{tranDetailList=").append(tranDetailList);
        sb.append('}');
        return sb.toString();
    }

}
