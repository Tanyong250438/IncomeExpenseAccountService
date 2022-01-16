/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.service.impl;

import com.service.IEAS.dao.PckUserDao;
import com.service.IEAS.domain.UserAuthentication;
import com.service.IEAS.service.LoggerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author tanyong
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private LoggerService logger;
    
    @Autowired
    private PckUserDao pckUserDao;

    @Override
    public UserAuthentication loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthentication userAuthenticationResultDB = null;
        try {
            UserAuthentication userAuthenticationQuery = new UserAuthentication();
            userAuthenticationQuery.setUsername(username);
            List<UserAuthentication> userAuthenticationList = pckUserDao.find(userAuthenticationQuery, null);
            userAuthenticationResultDB = userAuthenticationList != null
                    && userAuthenticationList.size() > 0 ? userAuthenticationList.get(0) : null;
        }catch (CredentialsExpiredException e) {
            logger.printStackTraceToErrorLog(username, e.getClass().toString(), e);
            throw e;
        } catch (AuthenticationException e) {
            logger.printStackTraceToErrorLog(username, e.getClass().toString(), e);
            throw e;

        } catch (Exception e) {
            logger.printStackTraceToErrorLog(username, e.getClass().toString(), e);
        }

        return userAuthenticationResultDB;
    }

}
