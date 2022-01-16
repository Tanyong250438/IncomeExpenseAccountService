/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.model.rest.response;

import java.util.List;

/**
 *
 * @author tanyong
 */
public class GetMenuResponse {
    private String name;
    private String profileImage;
    private List<ListMenu> listMenus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public List<ListMenu> getListMenus() {
        return listMenus;
    }

    public void setListMenus(List<ListMenu> listMenus) {
        this.listMenus = listMenus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GetMenuResponse{name=").append(name);
        sb.append(", profileImage=").append(profileImage);
        sb.append(", listMenus=").append(listMenus);
        sb.append('}');
        return sb.toString();
    }
    
    
}
