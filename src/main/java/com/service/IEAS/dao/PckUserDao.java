/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.dao;

import com.service.IEAS.domain.UserAuthentication;
import java.util.List;

/**
 *
 * @author tanyong
 */
public interface PckUserDao {

    public List<UserAuthentication> find(UserAuthentication findObject, String searchUsername) throws Exception;

    public List<UserAuthentication> findAllUser(UserAuthentication findObject, String searchKey) throws Exception;

    public void insert(UserAuthentication insertObject) throws Exception;

    public void update(UserAuthentication updateObject) throws Exception;
}
