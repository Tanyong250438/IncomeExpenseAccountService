/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.constant;

/**
 *
 * @author Tanyong
 */
public class CommonConstant {

    public static final String APPLICATION_NAME = "IEAS";
    public static final String APPLICATION_TIMEZONE_ASIA_BANGKOK = "Asia/Bangkok";
    public static final String APPLICATION_SECURITY_JACK = "JACK";
    public static final String APPLICATION_SYSTEM = "SYSTEM";
    public static final String APPLICATION_USER_DETAILS = "USER_DETAILS";
    public static final String UTF_8 = "UTF-8";

    public static final String APPLICATION_NUMBER_DECIMAL_REGEX = "(\\d+\\.?\\d*$)?";
    public static final String LATITUDE_AND_LONGITUDE_REGEX = "^[+-]?(([1-8]?[0-9])(\\.[0-9]{1,6})?|90(\\.0{1,6})?)$|all|ALL";

    public static final String LANGUAGE_TH = "TH";
    public static final String LANGUAGE_EN = "EN";
    public static final String LANGUAGE = "language";
    public static final String APPLICATION_LANGUAGE_REGEX = "^TH|EN|th|en";
    public static final String APPLICATION_NUMBER_REGEX = "^[0-9]*$";

    public static final String ALL = "ALL";
    //----------->

    public static final String SESSION_STATUS_ACTIVE = "A";
    public static final String SESSION_STATUS_DEACTIVE = "D";
    public static final String SESSION_STATUS_INITIAL = "I";

    public static final String RESPONSE_SUCCESS = "success";
    public static final String RESPONSE_FAIL = "fail";
    public static final String RESPONSE_TIMED_OUT = "TIMED OUT";
    public static final String RESPONSE_MESSAGE_SUCCESS_EN = "Transaction Success.";
    public static final String RESPONSE_MESSAGE_SUCCESS_TH = "\u0e17\u0e33\u0e23\u0e32\u0e22\u0e01\u0e32\u0e23\u0e2a\u0e33\u0e40\u0e23\u0e47\u0e08";

    public static final String RESPONSE_CODE_SUCCESS = "000000";

    public static final String FLAG_Y = "Y";
    public static final String FLAG_N = "N";
    public static final String FLAG_A = "A";
    public static final String FLAG_I = "I";
    public static final String FLAG_L = "L";
    public static final String FLAG_D = "D";
    public static final String FLAG_O = "O";
    public static final String FLAG_F = "F";
    public static final String FLAG_E = "E";
    public static final String FLAG_B = "B";

    public static final String ORDER_BY_ASC = "ASC";
    public static final String ORDER_BY_DESC = "DESC";

    public static final String SIGN_SPACE = " ";
    public static final String SIGN_COMMA = ",";
    public static final String SIGN_EMPTY = "";
    public static final String SIGN_DOT = ".";
    public static final String SIGN_UNDERSCORE = "_";
    public static final String SIGN_SLASH = "/";

    public static final String STATUS_WAITING = "WAITING";
    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_ERROR = "ERROR";
    public static final String STATUS_FAIL = "FAIL";
    public static final String SERVER_GLOBAL_ERROR_CODE = "System error, Please try again later...";

    public static final String EXCEPTION_SPLIT_SIGN_COMMA = ",";

    public static final String ACCESS_LOG = "[ACCESS]";
    public static final String SYSTEM_LOG = "[SYSTEM]";
    public static final String ERROR_LOG = "[ERROR]";
    public static final String EXTERNAL_LOG = "[EXTERNAL]";

    public static final String LOG_LEVEL_TRACE = "TRACE";
    public static final String LOG_LEVEL_DEBUG = "DEBUG";
    public static final String LOG_LEVEL_INFO = "INFO";
    public static final String LOG_LEVEL_WARN = "WARN";
    public static final String LOG_LEVEL_ERROR = "ERROR";
    public static final String LOG_LEVEL_FATAL = "FATAL";

    public static final String LOG_DATABASE_QUERY = "Query";
    public static final String LOG_DATABASE_PARAMETERS = "Parameters";

    public static final String LOG_EXCEPTION = "EXCEPTION";
    public static final String LOG_SERVICE_EXCEPTION = "SERVICE_EXCEPTION";
    public static final String LOG_SERVICE_VALIDATION = "SERVICE_VALIDATION";
    public static final String LOG_DATABASE_EXCEPTION = "DATABASE_EXCEPTION";
    public static final String LOG_DATABASE_ACCESS_SQL_EXCEPTION = "DATABASE_ACCESS_SQL_EXCEPTION";

    public static final String PROPERTY_CONTENT_TYPE = "Content-Type";
    public static final String AUTHORIZATION = "Authorization";

    public static final String PARAMETER_KEY_SESSION_TIMEOUT_BOS = "session.session_timeout_bos";
    public static final String PARAMETER_KEY_STATUS_SESSION = "SESSION";
    
    public static final String EXCEPTION_USER_NOT_FOUND = "10001";
    public static final String EXCEPTION_USER_LOCKED = "10002";
    public static final String EXCEPTION_USER_EXPIRED = "10003";
    public static final String EXCEPTION_USER_CREDENTIAL_EXPIRED = "10004";
    public static final String EXCEPTION_USER_NOT_ENABLED = "10005";
    
    public static final String AMPERSAND_MARK_SIGN = "&";
    public static final String QUESTION_MARK_SIGN = "?";
    public static final String EQUAL_MARK_SIGN = "=";
    public static final String ERROR_EXCEPTION_SPLIT_SIGN = ",";
}
