/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.dao;

import com.service.IEAS.domain.PckUserTransaction;
import java.util.List;

/**
 *
 * @author tanyong
 */
public interface PckUserTransactionDao {

    public List<PckUserTransaction> find(PckUserTransaction findObject) throws Exception;

    public void insert(String refID, List<PckUserTransaction> insertObjectList) throws Exception;

    public void update(String refID, PckUserTransaction updateObject) throws Exception;
}
