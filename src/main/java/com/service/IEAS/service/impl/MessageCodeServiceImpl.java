/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.service.impl;

import com.service.IEAS.constant.CommonConstant;
import com.service.IEAS.dao.MasErrorCodeDao;
import com.service.IEAS.domain.MasErrorCode;
import com.service.IEAS.service.LoggerService;
import com.service.IEAS.service.MessageCodeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tanyong
 */
@Service
public class MessageCodeServiceImpl implements MessageCodeService {

    @Autowired
    LoggerService logger;

    @Autowired
    MasErrorCodeDao errorCodeDao;

    @Override
    public String getMessageByMessageCode(String messageCode) {
        String message = null;

        if (HttpStatus.UNAUTHORIZED.toString().equals(messageCode)) {
            message = "Username of password incorrect.";

        } else if (HttpStatus.FORBIDDEN.toString().equals(messageCode)) {
            message = "You do not have permission to access.";

        } else if (HttpStatus.REQUEST_TIMEOUT.toString().equals(messageCode)) {
            message = "Session expired, please login again.";

        } else {
            try {

                MasErrorCode errorCodeQuery = new MasErrorCode();
                errorCodeQuery.setIsDelete(CommonConstant.FLAG_N);
                errorCodeQuery.setErrorCode(messageCode);
                List<MasErrorCode> errorCodeListDB = errorCodeDao.find(errorCodeQuery);
                MasErrorCode errorCodeDB = errorCodeListDB != null && errorCodeListDB.size() > 0 ? errorCodeListDB.get(0) : null;
                message = errorCodeDB != null ? errorCodeDB.getErrorEN() : null;

            } catch (Exception e) {
                logger.printStackTraceToErrorLog(messageCode, e.getClass().toString(), e);
            }
        }

        return message;
    }

}
