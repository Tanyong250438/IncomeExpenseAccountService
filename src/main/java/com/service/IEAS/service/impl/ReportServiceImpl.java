/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.service.impl;

import com.service.IEAS.dao.PckUserTransactionDao;
import com.service.IEAS.domain.PckUserTransaction;
import com.service.IEAS.model.rest.request.GetUserDailyReportRequest;
import com.service.IEAS.model.rest.response.GetUserDailyReportResponse;
import com.service.IEAS.model.rest.response.TranDetail;
import com.service.IEAS.service.LoggerService;
import com.service.IEAS.service.ReportService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author tanyong
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private LoggerService logger;

    @Autowired
    PckUserTransactionDao pckUserTransactionDao;

    @Override
    public GetUserDailyReportResponse getUserDailyReport(String userProfileName, GetUserDailyReportRequest request) throws Exception {
        GetUserDailyReportResponse response = null;
        try {
            List<TranDetail> tranDetailList = null;
            
            //select data
            PckUserTransaction pckUserTransactionFinder = new PckUserTransaction();
//            pckUserTransactionFinder.setUserID(request.get);
            pckUserTransactionFinder.setTranDate(null != request.getTranDate() ? request.getTranDate() : new Date());
            
        } catch (CredentialsExpiredException e) {
            logger.printStackTraceToErrorLog(userProfileName, e.getClass().toString(), e);
            throw e;
        } catch (AuthenticationException e) {
            logger.printStackTraceToErrorLog(userProfileName, e.getClass().toString(), e);
            throw e;

        } catch (Exception e) {
            logger.printStackTraceToErrorLog(userProfileName, e.getClass().toString(), e);
        }
        return response;
    }

}
