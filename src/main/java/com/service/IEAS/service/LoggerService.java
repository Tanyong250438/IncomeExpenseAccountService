/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.service;

import java.util.Date;

/**
 *
 * @author Tanyong
 */
public interface LoggerService {

    public void accessLogger(Date startDate, Date endDate, String refID, String result, Object input, Object output, String level);

    public void systemLogger(String refID, String message, Object result, String level);

    public void printStackTraceToErrorLog(String refID, String exceptionClassName, Throwable e);

}
