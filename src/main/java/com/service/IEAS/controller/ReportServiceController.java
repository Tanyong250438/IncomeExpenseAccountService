/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.controller;

import com.service.IEAS.constant.CommonConstant;
import com.service.IEAS.constant.ServiceNameConstant;
import com.service.IEAS.domain.UserAuthentication;
import com.service.IEAS.model.rest.request.GetUserDailyReportRequest;
import com.service.IEAS.model.rest.response.GetUserDailyReportResponse;
import com.service.IEAS.service.LoggerService;
import com.service.IEAS.service.ReportService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author tanyong
 */
@Controller
public class ReportServiceController {

    @Autowired
    private LoggerService logger;

    @Autowired
    private ReportService reportService;

    @PostMapping(ServiceNameConstant.SERVICE_GET_USER_DAILY_REPORT)
    @ResponseBody
    public Object getUserDailyReport(
            @RequestBody GetUserDailyReportRequest request, Authentication authentication) throws Exception {
        Date startDate = new Date();
        String userRef = authentication.getName();
        UserAuthentication detail = (UserAuthentication)authentication.getDetails();
        System.out.println(detail.toString());

        logger.accessLogger(startDate, new Date(), userRef,
                ServiceNameConstant.SERVICE_GET_USER_DAILY_REPORT + CommonConstant.BEGIN,
                request, null, CommonConstant.LOG_LEVEL_INFO
        );

        // Business begin
        GetUserDailyReportResponse response = reportService.getUserDailyReport(userRef, request);

        logger.accessLogger(startDate, new Date(), userRef,
                ServiceNameConstant.SERVICE_GET_USER_DAILY_REPORT + CommonConstant.END,
                request, response, CommonConstant.LOG_LEVEL_INFO
        );

        return response;

    }
}
