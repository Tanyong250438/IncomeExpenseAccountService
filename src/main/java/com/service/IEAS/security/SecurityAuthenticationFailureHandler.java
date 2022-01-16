/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.security;

import com.service.IEAS.constant.CommonConstant;
import com.service.IEAS.service.LoggerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anuwat_K
 */
@Component
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private LoggerService logger;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        StringBuilder sb = new StringBuilder();
        String exceptionCode = null;
        String exceptionMessage = null;

        if (exception instanceof InternalAuthenticationServiceException) {
            exceptionCode = CommonConstant.EXCEPTION_USER_NOT_FOUND;

        } else if (exception instanceof BadCredentialsException) {
            exceptionCode = CommonConstant.EXCEPTION_USER_NOT_FOUND;

        } else if (exception instanceof LockedException) {
            exceptionCode = CommonConstant.EXCEPTION_USER_LOCKED;

        } else if (exception instanceof CredentialsExpiredException) {
            exceptionCode = CommonConstant.EXCEPTION_USER_CREDENTIAL_EXPIRED;

        } else if (exception instanceof DisabledException) {
            exceptionCode = CommonConstant.EXCEPTION_USER_NOT_ENABLED;

        }

        //finding message from errorCode
//        exceptionMessage = messageCodeService.getMessageByMessageCode(exceptionCode);

        sb
                .append("/").append("loginException")
                .append(CommonConstant.QUESTION_MARK_SIGN)
                .append("exception").append(CommonConstant.EQUAL_MARK_SIGN).append("true")
                .append(CommonConstant.AMPERSAND_MARK_SIGN)
                .append("exceptionCode").append(CommonConstant.EQUAL_MARK_SIGN).append(exceptionCode)
                .append(CommonConstant.AMPERSAND_MARK_SIGN)
                .append("exceptionMessage").append(CommonConstant.EQUAL_MARK_SIGN).append(exceptionMessage);

        logger.systemLogger(null, "Authentication failure.", exception, CommonConstant.LOG_LEVEL_INFO);

        response.sendRedirect(sb.toString());
    }
}
