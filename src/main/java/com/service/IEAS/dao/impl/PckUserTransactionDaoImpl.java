/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.dao.impl;

import com.service.IEAS.constant.CommonConstant;
import com.service.IEAS.dao.PckUserTransactionDao;
import com.service.IEAS.domain.PckUserTransaction;
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
 * @author tanyong
 */
@Repository
public class PckUserTransactionDaoImpl implements PckUserTransactionDao {

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String TABLE = "pck_user_transaction";
    private final String CREATE_DATE = "create_date";
    private final String CREATE_BY = "create_by";
    private final String UPDATE_DATE = "update_date";
    private final String UPDATE_BY = "update_by";
    private final String IS_DELETE = "is_delete";

    private final String TRAN_ID = "tran_id";
    private final String USER_ID = "user_id";
    private final String TRAN_TYPE = "tran_type";
    private final String TRAN_AMOUNT = "tran_amount";
    private final String TRAN_DATE = "tran_date";
    private final String TRAN_DETAIL = "tran_detail";

    final RowMapper<PckUserTransaction> ROW_MAPPER = (ResultSet rs, int i) -> {
        final PckUserTransaction mapperObject = new PckUserTransaction();
        mapperObject.setCreateDate(rs.getTimestamp(CREATE_DATE));
        mapperObject.setCreateBy(rs.getString(CREATE_BY));
        mapperObject.setUpdateDate(rs.getTimestamp(UPDATE_DATE));
        mapperObject.setUpdateBy(rs.getString(UPDATE_BY));
        mapperObject.setIsDelete(rs.getString(IS_DELETE));

        mapperObject.setTranID(rs.getLong(TRAN_ID));
        mapperObject.setUserID(rs.getString(USER_ID));
        mapperObject.setTranType(rs.getString(TRAN_TYPE));
        mapperObject.setTranAmount(rs.getString(TRAN_AMOUNT));
        mapperObject.setTranDate(rs.getDate(TRAN_DATE));
        mapperObject.setTranDetail(rs.getString(TRAN_DETAIL));

        return mapperObject;

    };

    @Override
    public List<PckUserTransaction> find(PckUserTransaction findObject) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<PckUserTransaction> resultList = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        try {

            sql.append(" select * from ").append(TABLE);
            sql.append(" where 0 = 0 ");

            if (findObject != null) {

                if (StringUtils.isNotEmptyOrNull(findObject.getIsDelete())) {
                    sql.append(" and ").append(IS_DELETE).append(" = ? ");
                    parameters.add(findObject.getIsDelete());
                }
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

                if (null != findObject.getTranID()) {
                    sql.append(" and ").append(TRAN_ID).append(" = ? ");
                    parameters.add(findObject.getTranID());
                }

                if (StringUtils.isNotEmptyOrNull(findObject.getUserID())) {
                    sql.append(" and ").append(USER_ID).append(" = ? ");
                    parameters.add(findObject.getUserID());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getTranAmount())) {
                    sql.append(" and ").append(TRAN_AMOUNT).append(" = ? ");
                    parameters.add(findObject.getTranAmount());
                }

                if (null != findObject.getTranDate()) {
                    sql.append(" and ").append(TRAN_DATE).append(" = ? ");
                    parameters.add(findObject.getTranDate());
                }
                
                if (StringUtils.isNotEmptyOrNull(findObject.getTranDetail())) {
                    sql.append(" and ").append(TRAN_DETAIL).append(" = ? ");
                    parameters.add(findObject.getTranDetail());
                }
                
                
                sql.append(" order by ").append(TRAN_DATE).append(",").append(CREATE_DATE);
                loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString(), CommonConstant.LOG_LEVEL_DEBUG);
                loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()), CommonConstant.LOG_LEVEL_DEBUG);
            }
            
            resultList = jdbcTemplate.query(sql.toString(), parameters.toArray(), ROW_MAPPER);

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(findObject != null ? findObject.getUserID(): null, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;

        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(findObject != null ? findObject.getUserID() : null, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }

        return resultList;

    }

    @Override
    public void insert(String refID, List<PckUserTransaction> insertObjectList) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(String refID, PckUserTransaction updateObject) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
