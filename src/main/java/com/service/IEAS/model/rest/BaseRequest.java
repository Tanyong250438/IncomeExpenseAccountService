package com.service.api.model.rest;

public class BaseRequest {

    private String sid;
    private String language;
    private String decryptData;

    private boolean encryptFlag;
    private String BaseRose;
    private String headerRoleID;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDecryptData() {
        return decryptData;
    }

    public void setDecryptData(String decryptData) {
        this.decryptData = decryptData;
    }

    public boolean isEncryptFlag() {
        return encryptFlag;
    }

    public void setEncryptFlag(boolean encryptFlag) {
        this.encryptFlag = encryptFlag;
    }

    public String getBaseRose() {
        return BaseRose;
    }

    public void setBaseRose(String BaseRose) {
        this.BaseRose = BaseRose;
    }

    public String getHeaderRoleID() {
        return headerRoleID;
    }

    public void setHeaderRoleID(String headerRoleID) {
        this.headerRoleID = headerRoleID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BaseRequest{sid=").append(sid);
        sb.append(", language=").append(language);
        sb.append(", decryptData=").append(decryptData);
        sb.append(", encryptFlag=").append(encryptFlag);
        sb.append(", BaseRose=").append(BaseRose);
        sb.append(", headerRoleID=").append(headerRoleID);
        sb.append('}');
        return sb.toString();
    }

}
