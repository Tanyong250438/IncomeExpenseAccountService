/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.model.rest.response;

/**
 *
 * @author tanyong
 */
public class ListMenu {

    private String bosMenuCode;
    private String bosMenuService;
    private String bosMenuNameTH;
    private String bosMenuNameEN;
    private String bosLastFileName;
    private String bosLastFilePath;

    public String getBosMenuCode() {
        return bosMenuCode;
    }

    public void setBosMenuCode(String bosMenuCode) {
        this.bosMenuCode = bosMenuCode;
    }

    public String getBosMenuService() {
        return bosMenuService;
    }

    public void setBosMenuService(String bosMenuService) {
        this.bosMenuService = bosMenuService;
    }

    public String getBosMenuNameTH() {
        return bosMenuNameTH;
    }

    public void setBosMenuNameTH(String bosMenuNameTH) {
        this.bosMenuNameTH = bosMenuNameTH;
    }

    public String getBosMenuNameEN() {
        return bosMenuNameEN;
    }

    public void setBosMenuNameEN(String bosMenuNameEN) {
        this.bosMenuNameEN = bosMenuNameEN;
    }

    public String getBosLastFileName() {
        return bosLastFileName;
    }

    public void setBosLastFileName(String bosLastFileName) {
        this.bosLastFileName = bosLastFileName;
    }

    public String getBosLastFilePath() {
        return bosLastFilePath;
    }

    public void setBosLastFilePath(String bosLastFilePath) {
        this.bosLastFilePath = bosLastFilePath;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ListMenu{bosMenuCode=").append(bosMenuCode);
        sb.append(", bosMenuService=").append(bosMenuService);
        sb.append(", bosMenuNameTH=").append(bosMenuNameTH);
        sb.append(", bosMenuNameEN=").append(bosMenuNameEN);
        sb.append(", bosLastFileName=").append(bosLastFileName);
        sb.append(", bosLastFilePath=").append(bosLastFilePath);
        sb.append('}');
        return sb.toString();
    }

}
