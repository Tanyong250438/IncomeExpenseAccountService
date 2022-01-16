/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.service.impl;

import com.service.IEAS.constant.CommonConstant;
import com.service.IEAS.service.LoggerService;
import com.service.IEAS.service.MessageCodeService;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tanyong
 */
@Service
public class LoggerServiceImpl implements LoggerService {

    @Autowired
    private MessageCodeService messageCodeService;

    private static final String sFlag = "<";
    private static final String eFlag = ">";
    private static final String START_SQUARE_BRACKETS = "[";
    private static final String END_SQUARE_BRACKETS = "]";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    private Logger accessLogger = LoggerFactory.getLogger(CommonConstant.ACCESS_LOG);
    private Logger externalLogger = LoggerFactory.getLogger(CommonConstant.EXTERNAL_LOG);
    private Logger systemLogger = LoggerFactory.getLogger(CommonConstant.SYSTEM_LOG);
    private Logger errorLogger = LoggerFactory.getLogger(CommonConstant.ERROR_LOG);

    public void accessLogger(Date startDate, Date endDate, String refID, String result, Object input, Object output, String level) {
        StringBuilder sb = new StringBuilder();

        // ReferenceID
        sb.append("RefID=");
        sb.append(START_SQUARE_BRACKETS);
        sb.append(refID == null ? "" : refID);
        sb.append(", ");

        //Time
        sb.append(dateFormat.format(startDate));
        sb.append(", ");
        sb.append(dateFormat.format(endDate));
        sb.append(", ");
        sb.append((endDate.getTime() - startDate.getTime()));
        sb.append(END_SQUARE_BRACKETS);
        sb.append(" ");

        //Result
        sb.append("Result=");
        sb.append(START_SQUARE_BRACKETS);
        sb.append(result == null ? "" : result);
        sb.append(END_SQUARE_BRACKETS);
        sb.append(" ");

        //Resquest
        sb.append("Request=");
        sb.append(START_SQUARE_BRACKETS);
        sb.append(input == null ? "" : input.toString());
        sb.append(END_SQUARE_BRACKETS);
        sb.append(" ");

        // Response
        sb.append("Response=");
        sb.append(START_SQUARE_BRACKETS);
        sb.append(output == null ? "" : output.toString());
        sb.append(END_SQUARE_BRACKETS);

        if (CommonConstant.LOG_LEVEL_DEBUG.equalsIgnoreCase(level)) {
            accessLogger.debug(sb.toString());
        } else if (CommonConstant.LOG_LEVEL_INFO.equalsIgnoreCase(level)) {
            accessLogger.info(sb.toString());
        } else if (CommonConstant.LOG_LEVEL_WARN.equalsIgnoreCase(level)) {
            accessLogger.warn(sb.toString());
        } else {
            accessLogger.trace(sb.toString());
        }
    }

    public void accessExternalLogger(Date startDate, Date endDate, String refID, String result, Object input, Object output, String level) {
        StringBuilder sb = new StringBuilder();

        // ReferenceID
        sb.append("RefID=");
        sb.append(START_SQUARE_BRACKETS);
        sb.append(refID == null ? "" : refID);
        sb.append(", ");

        //Time
        sb.append(dateFormat.format(startDate));
        sb.append(", ");
        sb.append(dateFormat.format(endDate));
        sb.append(", ");
        sb.append((endDate.getTime() - startDate.getTime()));
        sb.append(END_SQUARE_BRACKETS);
        sb.append(" ");

        //Result
        sb.append("Result=");
        sb.append(START_SQUARE_BRACKETS);
        sb.append(result == null ? "" : result);
        sb.append(END_SQUARE_BRACKETS);
        sb.append(" ");

        //Resquest
        sb.append("Request=");
        sb.append(START_SQUARE_BRACKETS);
        sb.append(input == null ? "" : input.toString());
        sb.append(END_SQUARE_BRACKETS);
        sb.append(" ");

        // Response
        sb.append("Response=");
        sb.append(START_SQUARE_BRACKETS);
        sb.append(output == null ? "" : output.toString());
        sb.append(END_SQUARE_BRACKETS);

        if (CommonConstant.LOG_LEVEL_DEBUG.equalsIgnoreCase(level)) {
            externalLogger.debug(sb.toString());
        } else if (CommonConstant.LOG_LEVEL_INFO.equalsIgnoreCase(level)) {
            externalLogger.info(sb.toString());
        } else if (CommonConstant.LOG_LEVEL_WARN.equalsIgnoreCase(level)) {
            externalLogger.warn(sb.toString());
        } else {
            externalLogger.trace(sb.toString());
        }
    }

    public void systemLogger(String refID, String message, Object result, String level) {
        StringBuilder sb = new StringBuilder();

        // ReferenceID
        sb.append("RefID=[");
        sb.append(refID == null ? "" : refID);
        sb.append("]");
        sb.append(" ");

        //Result
        sb.append("Message=[");
        sb.append(message == null ? "" : message);
        sb.append("]");
        sb.append(" ");

        //Object
        sb.append("Result=[");
        sb.append(result == null ? "" : result.toString());
        sb.append("]");
        sb.append(" ");

        if (CommonConstant.LOG_LEVEL_DEBUG.equalsIgnoreCase(level)) {
            systemLogger.debug(sb.toString());
        } else if (CommonConstant.LOG_LEVEL_INFO.equalsIgnoreCase(level)) {
            systemLogger.info(sb.toString());
        } else if (CommonConstant.LOG_LEVEL_WARN.equalsIgnoreCase(level)) {
            systemLogger.warn(sb.toString());
        } else {
            systemLogger.trace(sb.toString());
        }
    }

    @Override
    public void printStackTraceToErrorLog(String refID, String exceptionClassName, Throwable e) {
        StringBuilder str = new StringBuilder();

//        String message = messageCodeService.getMessageByMessageCode(e.getMessage());
        str.append("[RefID: ").append(refID).append("] ");
        str.append("[Type: ").append(exceptionClassName).append("] ");
        str.append("[MessageCode: ").append(e.getMessage()).append("] ");
//        str.append("[Message: ").append(message).append("] ");

        if (e != null) {
            StackTraceElement elements[] = e.getStackTrace();
            if (elements != null && elements.length > 0) {
                str.append(e.toString());
                for (int i = 0, n = elements.length; i < n; i++) {
                    str.append(" at ");
                    str.append(elements[i].getClassName()).append(" (").append(elements[i].getMethodName())
                            .append(":").append(elements[i].getLineNumber()).append(")\n");
                }

            } else {
                str.append(e.toString());
            }

            if (e.getCause() != null) {
                StackTraceElement elements2[] = e.getCause().getStackTrace();
                if (elements2 != null && elements2.length != 0) {
                    str.append(" Caused by : ");
                    str.append(e.getCause().toString());
                    str.append(" at ");
                    for (int i = 0; i < elements2.length; i++) {
                        str.append(" at ");
                        str.append(elements2[i].getClassName()).append("(").append(elements2[i].getMethodName())
                                .append(":").append(elements2[i].getLineNumber()).append(")\n");
                    }

                } else {
                    str.append(e.getCause().toString());
                }
            }
        }

        errorLogger.error(str.toString());
    }
}
