/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.dao.impl;

import com.mysql.cj.xdevapi.Schema;
import com.service.IEAS.constant.CommonConstant;
import com.service.IEAS.dao.PckUserDao;
import com.service.IEAS.domain.UserAuthentication;
import com.service.IEAS.service.LoggerService;
import com.service.IEAS.util.StringUtils;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tanyong
 */
@Repository
public class PckUserDaoImpl implements PckUserDao {

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String TABLE = "pck_user";
    private final String CREATE_DATE = "create_date";
    private final String CREATE_BY = "create_by";
    private final String UPDATE_DATE = "update_date";
    private final String UPDATE_BY = "update_by";
    private final String IS_DELETE = "is_delete";

    private final String USER_ID = "user_id";
    private final String USER_NAME = "user_name";
    private final String USER_EMAIL = "user_email";
    private final String JACK = "jack";
    private final String ACCOUNT_NON_LOCKED = "account_non_locked";

    private final String CREDENTIALS_NON_EXPIRED = "credentials_non_expired";
    private final String ENABLED = "enabled";
    private final String AUTHORIZE = "authorize";

    private final String PICTURE = "picture";
    private final String FIRSTNAME = "first_name";
    private final String LASTNAME = "last_name";
    private final String UPDATE_JACK_DATE = "update_jack_date";

    final RowMapper<UserAuthentication> ROW_MAPPER = (ResultSet rs, int i) -> {

        final UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setCreateDate(rs.getTimestamp(CREATE_DATE));
        userAuthentication.setCreateBy(rs.getString(CREATE_BY));
        userAuthentication.setUpdateDate(rs.getTimestamp(UPDATE_DATE));
        userAuthentication.setUpdateBy(UPDATE_BY);
        userAuthentication.setIsDelete(rs.getString(IS_DELETE));

        userAuthentication.setUserID(rs.getString(USER_ID));
        userAuthentication.setUsername(rs.getString(USER_NAME));
        userAuthentication.setUserEmail(rs.getString(USER_EMAIL));
        userAuthentication.setPassword(rs.getString(JACK));
        userAuthentication.setIsAccountNonLocked(rs.getBoolean(ACCOUNT_NON_LOCKED));

        userAuthentication.setIsCredentialsNonExpired(rs.getBoolean(CREDENTIALS_NON_EXPIRED));
        userAuthentication.setIsEnabled(rs.getBoolean(ENABLED));
        userAuthentication.setAuthorizeList(grantedAuthorityMapper(rs.getString(AUTHORIZE)));

        userAuthentication.setPicture(rs.getString(PICTURE));
        userAuthentication.setFirstName(rs.getString(FIRSTNAME));
        userAuthentication.setLastName(rs.getString(LASTNAME));
        userAuthentication.setUpdateJackDate(rs.getTimestamp(UPDATE_JACK_DATE));

        loggerService.systemLogger(TABLE, "Current_Row", i, CommonConstant.LOG_LEVEL_DEBUG);
        return userAuthentication;
    };

    @Override
    public List<UserAuthentication> find(UserAuthentication findObject, String searchKey) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<UserAuthentication> resultList = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();
        try {

            sql.append(" select * from ").append(TABLE);
            sql.append(" where 0 = 0 ");

            if (findObject != null) {
                if (findObject.getCreateDate() != null) {
                    sql.append(" and ").append(CREATE_DATE).append(" = ? ");
                    parameters.add(findObject.getCreateDate());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getCreateBy())) {
                    sql.append(" and ").append(CREATE_BY).append(" = ? ");
                    parameters.add(findObject.getCreateBy());
                }
                if (findObject.getUpdateDate() != null) {
                    sql.append(" and ").append(UPDATE_DATE).append(" = ? ");
                    parameters.add(findObject.getUpdateDate());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getUpdateBy())) {
                    sql.append(" and ").append(UPDATE_BY).append(" = ? ");
                    parameters.add(findObject.getUpdateBy());
                }

                if (StringUtils.isNotEmptyOrNull(findObject.getUserID())) {
                    sql.append(" and ").append(USER_ID).append(" = ? ");
                    parameters.add(findObject.getUserID());
                }

                if (StringUtils.isNotEmptyOrNull(findObject.getPicture())) {
                    sql.append(" and ").append(PICTURE).append(" = ? ");
                    parameters.add(findObject.getPicture());
                }

                if (StringUtils.isNotEmptyOrNull(findObject.getFirstName())) {
                    sql.append(" and ").append(FIRSTNAME).append(" = ? ");
                    parameters.add(findObject.getFirstName());
                }

                if (StringUtils.isNotEmptyOrNull(findObject.getLastName())) {
                    sql.append(" and ").append(LASTNAME).append(" = ? ");
                    parameters.add(findObject.getLastName());
                }

                if (StringUtils.isNotEmptyOrNull(searchKey)) {
                    sql.append(" and ").append(USER_NAME).append(" like ? ");
                    parameters.add("%" + searchKey + "%");

                } else {
                    if (StringUtils.isNotEmptyOrNull(findObject.getUsername())) {
                        sql.append(" and ").append(USER_NAME).append(" = ? ");
                        parameters.add(findObject.getUsername());
                    }
                }

                sql.append(" and ").append(IS_DELETE).append(" = ? ");
                parameters.add(CommonConstant.FLAG_N);
            }

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString(), CommonConstant.LOG_LEVEL_DEBUG);
            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()), CommonConstant.LOG_LEVEL_DEBUG);

            resultList = jdbcTemplate.query(sql.toString(), parameters.toArray(), ROW_MAPPER);

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(findObject != null ? String.valueOf(findObject.getUserID()) : null, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;

        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(findObject != null ? String.valueOf(findObject.getUserID()) : null, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }

        return resultList;
    }

    @Override
    public List<UserAuthentication> findAllUser(UserAuthentication findObject, String searchKey) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<UserAuthentication> resultList = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        try {

            sql.append(" select * from ").append(TABLE);
            sql.append(" where 0 = 0 ");

            if (findObject != null) {
                if (findObject.getCreateDate() != null) {
                    sql.append(" and ").append(CREATE_DATE).append(" = ? ");
                    parameters.add(findObject.getCreateDate());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getCreateBy())) {
                    sql.append(" and ").append(CREATE_BY).append(" = ? ");
                    parameters.add(findObject.getCreateBy());
                }
                if (findObject.getUpdateDate() != null) {
                    sql.append(" and ").append(UPDATE_DATE).append(" = ? ");
                    parameters.add(findObject.getUpdateDate());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getUpdateBy())) {
                    sql.append(" and ").append(UPDATE_BY).append(" = ? ");
                    parameters.add(findObject.getUpdateBy());
                }

                if (StringUtils.isNotEmptyOrNull(findObject.getPicture())) {
                    sql.append(" and ").append(PICTURE).append(" = ? ");
                    parameters.add(findObject.getPicture());
                }

                if (StringUtils.isNotEmptyOrNull(findObject.getFirstName())) {
                    sql.append(" and ").append(FIRSTNAME).append(" = ? ");
                    parameters.add(findObject.getFirstName());
                }

                if (StringUtils.isNotEmptyOrNull(findObject.getLastName())) {
                    sql.append(" and ").append(LASTNAME).append(" = ? ");
                    parameters.add(findObject.getLastName());
                }

                if (StringUtils.isNotEmptyOrNull(findObject.getUserID())) {
                    sql.append(" and ").append(USER_ID).append(" = ? ");
                    parameters.add(findObject.getUserID());
                }

                if (StringUtils.isNotEmptyOrNull(searchKey)) {
                    sql.append(" and ").append(USER_NAME).append(" like ? ");
                    parameters.add("%" + searchKey + "%");

                } else {
                    if (StringUtils.isNotEmptyOrNull(findObject.getUsername())) {
                        sql.append(" and ").append(USER_NAME).append(" = ? ");
                        parameters.add(findObject.getUsername());
                    }
                }

            }

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString(), CommonConstant.LOG_LEVEL_DEBUG);
            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()), CommonConstant.LOG_LEVEL_DEBUG);

            resultList = jdbcTemplate.query(sql.toString(), parameters.toArray(), ROW_MAPPER);

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(findObject != null ? String.valueOf(findObject.getUserID()) : null, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;

        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(findObject != null ? String.valueOf(findObject.getUserID()) : null, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }

        return resultList;
    }

    @Override
    public void insert(UserAuthentication insertObject) throws Exception {
        StringBuilder sql = new StringBuilder();
        ArrayList<Object> parameters = new ArrayList<>();

        try {

            if (insertObject != null) {

                //setup column
                sql.append(" insert into ").append(TABLE).append(" (")
                        .append(IS_DELETE).append(",")
                        .append(CREATE_DATE).append(",")
                        .append(CREATE_BY).append(",");

                sql
                        .append(USER_ID).append(",")
                        .append(USER_NAME).append(",")
                        .append(USER_EMAIL).append(",")
                        .append(JACK).append(",")
                        .append(ENABLED).append(",")
                        .append(CREDENTIALS_NON_EXPIRED).append(",")
                        .append(AUTHORIZE).append(",")
                        .append(FIRSTNAME).append(",")
                        .append(LASTNAME).append(",")
                        .append(PICTURE).append(",")
                        .append(ACCOUNT_NON_LOCKED)
                        .append(")");

                sql.append(" values (")
                        .append(" ? ").append(",")
                        .append(" ? ").append(",")
                        .append(" ? ").append(",")
                        .append(" ? ").append(",")
                        .append(" ? ").append(",")
                        .append(" ? ").append(",")
                        .append(" ? ").append(",")
                        .append(" ? ").append(",")
                        .append(" ? ").append(",")
                        .append(" ? ").append(",")
                        .append(" ? ").append(",")
                        .append(" ? ").append(",")
                        .append(" ? ").append(",")
                        .append(" ? ")
                        .append(")");

                parameters.add(insertObject.getIsDelete());
                parameters.add(insertObject.getCreateDate());
                parameters.add(insertObject.getCreateBy());

                parameters.add(insertObject.getUserID());
                parameters.add(insertObject.getUsername());
                parameters.add(insertObject.getUserEmail());
                parameters.add(insertObject.getPassword());
                parameters.add(insertObject.isEnabled());
                parameters.add(insertObject.isCredentialsNonExpired());
                parameters.add(grantedAuthorityConvert((List<GrantedAuthority>) insertObject.getAuthorities()));

                parameters.add(insertObject.getFirstName());
                parameters.add(insertObject.getLastName());
                parameters.add(insertObject.getPicture());

                parameters.add(insertObject.isAccountNonLocked());

            }

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString(), CommonConstant.LOG_LEVEL_DEBUG);
            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()), CommonConstant.LOG_LEVEL_DEBUG);

            jdbcTemplate.update(sql.toString(), parameters.toArray());

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(insertObject != null ? String.valueOf(insertObject.getUserID()) : null, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;

        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(insertObject != null ? String.valueOf(insertObject.getUserID()) : null, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;

        }
    }

    @Override
    public void update(UserAuthentication updateObject) throws Exception {
        ArrayList<Object> parameters = new ArrayList<>();
        StringBuilder sql = new StringBuilder();

        try {

            sql.append(" update ").append(TABLE).append(" SET ");

            if (updateObject != null) {
                sql
                        .append(IS_DELETE).append(" = ? ").append(",")
                        .append(UPDATE_DATE).append(" = ? ").append(",")
                        .append(UPDATE_BY).append(" = ? ").append(",");

                parameters.add(updateObject.getIsDelete());
                parameters.add(updateObject.getUpdateDate());
                parameters.add(updateObject.getUpdateBy());

                if (StringUtils.isNotEmptyOrNull(updateObject.getPassword())) {
                    sql.append(JACK).append(" = ? ").append(",");
                    parameters.add(updateObject.getPassword());
                }
                if (updateObject.getUpdateJackDate() != null) {
                    sql.append(UPDATE_JACK_DATE).append(" = ? ").append(",");
                    parameters.add(updateObject.getUpdateJackDate());
                }

                sql
                        .append(ENABLED).append(" = ? ").append(",")
                        .append(CREDENTIALS_NON_EXPIRED).append(" = ? ").append(",")
                        .append(ACCOUNT_NON_LOCKED).append(" = ? ");

                parameters.add(updateObject.isEnabled() ? "1" : "0");
                parameters.add(updateObject.isCredentialsNonExpired() ? "1" : "0");
                parameters.add(updateObject.isAccountNonLocked() ? "1" : "0");

                if (updateObject.getAuthorities() != null) {
                    sql.append(",").append(AUTHORIZE).append(" = ? ");
                    parameters.add(grantedAuthorityConvert((List<GrantedAuthority>) updateObject.getAuthorities()));
                }

                sql
                        .append(" WHERE ").append(USER_NAME).append(" = ? ");

//                parameters.add(grantedAuthorityConvert((List<GrantedAuthority>) updateObject.getAuthorities()));
                parameters.add(updateObject.getUsername());

            }

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString(), CommonConstant.LOG_LEVEL_DEBUG);
            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()), CommonConstant.LOG_LEVEL_DEBUG);

            jdbcTemplate.update(sql.toString(), parameters.toArray());

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(updateObject != null ? String.valueOf(updateObject.getUserID()) : null, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;

        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(updateObject != null ? String.valueOf(updateObject.getUserID()) : null, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;

        }
    }

    private List<GrantedAuthority> grantedAuthorityMapper(String authorize) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        if (StringUtils.isNotEmptyOrNull(authorize)) {
            String[] authorizeArray = authorize.split(",");
            if (authorizeArray != null) {
                int length = authorizeArray.length;
                if (length > 0) {
                    if (length > 1) {
                        for (String auth : authorizeArray) {
                            grantedAuthorityList.add(new SimpleGrantedAuthority(auth));
                        }

                    } else {
                        grantedAuthorityList.add(new SimpleGrantedAuthority(authorizeArray[0]));
                    }
                }
            }
        }

        return grantedAuthorityList;
    }

    private String grantedAuthorityConvert(List<GrantedAuthority> authoritiesList) {
        StringBuilder authorityBuilder = new StringBuilder();

        if (authoritiesList != null && authoritiesList.size() > 0) {
            int size = authoritiesList.size();
            for (int i = 0; i < size; i++) {
                authorityBuilder.append(authoritiesList.get(i).getAuthority().trim());

                if (i < size - 1) {
                    authorityBuilder.append(",");
                }
            }
        }

        return authorityBuilder.toString();
    }

}
