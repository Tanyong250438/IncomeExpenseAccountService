/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.domain;

/**
 *
 * @author tanyong
 */
public class MasBosMenu extends BaseDomain {

    private String bosMenuCode;
    private String bosMenuService;
    private String bosMenuNameTH;
    private String bosMenuNameEN;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MasBosMenu{bosMenuCode=").append(bosMenuCode);
        sb.append(", bosMenuService=").append(bosMenuService);
        sb.append(", bosMenuNameTH=").append(bosMenuNameTH);
        sb.append(", bosMenuNameEN=").append(bosMenuNameEN);
        sb.append('}');
        return sb.toString();
    }

}
