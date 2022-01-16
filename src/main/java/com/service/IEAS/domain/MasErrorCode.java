/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.domain;

/**
 *
 * @author Tanyong
 */
public class MasErrorCode extends BaseDomain {

    private String errorCode;
    private String errorType;
    private String errorDesc;
    private String userInScreen;
    private String interfaceName;

    private String errorTH;
    private String errorEN;
    private String isDelete;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getUserInScreen() {
        return userInScreen;
    }

    public void setUserInScreen(String userInScreen) {
        this.userInScreen = userInScreen;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getErrorTH() {
        return errorTH;
    }

    public void setErrorTH(String errorTH) {
        this.errorTH = errorTH;
    }

    public String getErrorEN() {
        return errorEN;
    }

    public void setErrorEN(String errorEN) {
        this.errorEN = errorEN;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "ErrorCodeDetails:{" + "errorCode=" + errorCode + ", errorType=" + errorType + ", errorDesc=" + errorDesc + ", userInScreen=" + userInScreen + ", interfaceName=" + interfaceName + ", errorTH=" + errorTH + ", error_EN=" + errorEN + ", isDelete=" + isDelete + '}';
    }

}
