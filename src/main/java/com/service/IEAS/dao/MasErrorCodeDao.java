/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.dao;

import com.service.IEAS.domain.MasErrorCode;
import java.util.List;

/**
 *
 * @author Tanyong
 */
public interface MasErrorCodeDao {

    public List<MasErrorCode> find(MasErrorCode findObject) throws Exception;

}
