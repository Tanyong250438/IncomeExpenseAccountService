/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.dao;

import com.service.IEAS.domain.MasBosMenu;
import java.util.List;

/**
 *
 * @author tanyong
 */
public interface MasBosMenuDao {

    public List<MasBosMenu> find(MasBosMenu findObject) throws Exception;

    public void insert(List<MasBosMenu> insertObject) throws Exception;

    public void update(MasBosMenu updateObject) throws Exception;
}
