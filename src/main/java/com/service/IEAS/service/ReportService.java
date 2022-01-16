/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.service;

import com.service.IEAS.model.rest.request.GetUserDailyReportRequest;
import com.service.IEAS.model.rest.response.GetUserDailyReportResponse;

/**
 *
 * @author tanyong
 */
public interface ReportService {

    public GetUserDailyReportResponse getUserDailyReport(String userProfileName, GetUserDailyReportRequest request) throws Exception;

}
