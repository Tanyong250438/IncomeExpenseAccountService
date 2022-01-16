/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.dao.impl;

import com.service.IEAS.constant.CommonConstant;
import com.service.IEAS.dao.MasErrorCodeDao;
import com.service.IEAS.domain.MasErrorCode;
import com.service.IEAS.service.LoggerService;
import com.service.IEAS.util.StringUtils;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tanyong
 */
@Repository
public class MasErrorCodeDaoImpl implements MasErrorCodeDao {

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String TABLE = "mas_error_code";
    private final String CREATE_DATE = "create_date";
    private final String CREATE_BY = "create_by";
    private final String UPDATE_DATE = "update_date";
    private final String UPDATE_BY = "update_by";

    private final String ERROR_CODE = "error_code";
    private final String ERROR_TYPE = "error_type";
    private final String ERROR_DESC = "error_desc";
    private final String USER_IN_SCREEN = "user_in_screen";
    private final String INTERFACE_NAME = "interface_name";

    private final String ERROR_TH = "error_th";
    private final String ERROR_EN = "error_en";
    private final String IS_DELETE = "is_delete";

    final RowMapper<MasErrorCode> ERROR_CODE_ROW_MAPPER = (ResultSet rs, int i) -> {

        final MasErrorCode errorCode = new MasErrorCode();
        errorCode.setCreateDate(rs.getTimestamp(CREATE_DATE));
        errorCode.setCreateBy(rs.getString(CREATE_BY));
        errorCode.setUpdateDate(rs.getTimestamp(UPDATE_DATE));
        errorCode.setUpdateBy(UPDATE_BY);

        errorCode.setErrorCode(rs.getString(ERROR_CODE));
        errorCode.setErrorType(rs.getString(ERROR_TYPE));
        errorCode.setErrorDesc(rs.getString(ERROR_DESC));
        errorCode.setUserInScreen(rs.getString(USER_IN_SCREEN));
        errorCode.setInterfaceName(rs.getString(INTERFACE_NAME));

        errorCode.setErrorTH(rs.getString(ERROR_TH));
        errorCode.setErrorEN(rs.getString(ERROR_EN));
        errorCode.setIsDelete(rs.getString(IS_DELETE));

        loggerService.systemLogger(TABLE, "Current_Row", i, CommonConstant.LOG_LEVEL_DEBUG);
        return errorCode;
    };

    @Override
    public List<MasErrorCode> find(MasErrorCode findObject) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<MasErrorCode> resultList = new ArrayList<>();
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

                if (StringUtils.isNotEmptyOrNull(findObject.getErrorCode())) {
                    sql.append(" and ").append(ERROR_CODE).append(" = ? ");
                    parameters.add(findObject.getErrorCode());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getErrorType())) {
                    sql.append(" and ").append(ERROR_TYPE).append(" = ? ");
                    parameters.add(findObject.getErrorType());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getErrorDesc())) {
                    sql.append(" and ").append(ERROR_DESC).append(" = ? ");
                    parameters.add(findObject.getErrorDesc());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getUserInScreen())) {
                    sql.append(" and ").append(USER_IN_SCREEN).append(" = ? ");
                    parameters.add(findObject.getUserInScreen());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getInterfaceName())) {
                    sql.append(" and ").append(INTERFACE_NAME).append(" = ? ");
                    parameters.add(findObject.getInterfaceName());
                }

                if (StringUtils.isNotEmptyOrNull(findObject.getErrorTH())) {
                    sql.append(" and ").append(ERROR_TH).append(" = ? ");
                    parameters.add(findObject.getErrorTH());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getErrorEN())) {
                    sql.append(" and ").append(ERROR_EN).append(" = ? ");
                    parameters.add(findObject.getErrorEN());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getIsDelete())) {
                    sql.append(" and ").append(IS_DELETE).append(" = ? ");
                    parameters.add(findObject.getIsDelete());
                }

            }

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString(), CommonConstant.LOG_LEVEL_DEBUG);
            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()), CommonConstant.LOG_LEVEL_DEBUG);

            resultList = jdbcTemplate.query(sql.toString(), parameters.toArray(), ERROR_CODE_ROW_MAPPER);

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(findObject != null ? String.valueOf(findObject.getErrorCode()) : null, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;

        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(findObject != null ? String.valueOf(findObject.getErrorCode()) : null, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }

        return resultList;
    }

}
