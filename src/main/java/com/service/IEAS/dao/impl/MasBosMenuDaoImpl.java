/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.dao.impl;

import com.service.IEAS.constant.CommonConstant;
import com.service.IEAS.dao.MasBosMenuDao;
import com.service.IEAS.domain.MasBosMenu;
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
import org.springframework.stereotype.Repository;

/**
 *
 * @author tanyong
 */
@Repository
public class MasBosMenuDaoImpl implements MasBosMenuDao {

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String TABLE = "mas_bos_menu";
    private final String CREATE_DATE = "create_date";
    private final String CREATE_BY = "create_by";
    private final String UPDATE_DATE = "update_date";
    private final String UPDATE_BY = "update_by";
    private final String IS_DELETE = "is_delete";

    private final String BOS_MENU_CODE = "bos_menu_code";
    private final String BOS_MENU_SERVICE = "bos_menu_service";
    private final String BOS_MENU_NAME_TH = "bos_menu_name_th";
    private final String BOS_MENU_NAME_EN = "bos_menu_name_en";

    final RowMapper<MasBosMenu> ROW_MAPPER = (ResultSet rs, int i) -> {
        final MasBosMenu mapperObject = new MasBosMenu();
        mapperObject.setCreateDate(rs.getTimestamp(CREATE_DATE));
        mapperObject.setCreateBy(rs.getString(CREATE_BY));
        mapperObject.setUpdateDate(rs.getTimestamp(UPDATE_DATE));
        mapperObject.setUpdateBy(rs.getString(UPDATE_BY));
        mapperObject.setIsDelete(rs.getString(IS_DELETE));

        mapperObject.setBosMenuCode(rs.getString(BOS_MENU_CODE));
        mapperObject.setBosMenuService(rs.getString(BOS_MENU_SERVICE));
        mapperObject.setBosMenuNameTH(rs.getString(BOS_MENU_NAME_TH));
        mapperObject.setBosMenuNameEN(rs.getString(BOS_MENU_NAME_EN));

        return mapperObject;

    };

    @Override
    public List<MasBosMenu> find(MasBosMenu findObject) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<MasBosMenu> resultList = new ArrayList<>();
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

                if (StringUtils.isNotEmptyOrNull(findObject.getBosMenuCode())) {
                    sql.append(" and ").append(BOS_MENU_CODE).append(" = ? ");
                    parameters.add(findObject.getBosMenuCode());
                }

                if (StringUtils.isNotEmptyOrNull(findObject.getBosMenuService())) {
                    sql.append(" and ").append(BOS_MENU_SERVICE).append(" = ? ");
                    parameters.add(findObject.getBosMenuService());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getBosMenuNameTH())) {
                    sql.append(" and ").append(BOS_MENU_NAME_TH).append(" = ? ");
                    parameters.add(findObject.getBosMenuNameTH());
                }

                if (StringUtils.isNotEmptyOrNull(findObject.getBosMenuNameEN())) {
                    sql.append(" and ").append(BOS_MENU_NAME_EN).append(" = ? ");
                    parameters.add(findObject.getBosMenuNameEN());
                }
                sql.append(" order by ").append(BOS_MENU_CODE);
                loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString(), CommonConstant.LOG_LEVEL_DEBUG);
                loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()), CommonConstant.LOG_LEVEL_DEBUG);
            }
            resultList = jdbcTemplate.query(sql.toString(), parameters.toArray(), ROW_MAPPER);

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(findObject != null ? findObject.getBosMenuCode() : null, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;

        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(findObject != null ? findObject.getBosMenuCode() : null, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }

        return resultList;

    }

    @Override
    public void insert(List<MasBosMenu> insertObjectList) throws Exception {
        StringBuilder sql = new StringBuilder();
        StringBuilder prepareObject = new StringBuilder();
        ArrayList<Object> parameters = new ArrayList<>();

        try {

            //setup column 
            sql
                    .append(" insert into ").append(TABLE).append(" (")
                    .append(CREATE_DATE).append(",")
                    .append(CREATE_BY).append(",")
                    .append(IS_DELETE).append(",");

            sql
                    .append(BOS_MENU_CODE).append(",")
                    .append(BOS_MENU_SERVICE).append(",")
                    .append(BOS_MENU_NAME_TH).append(",")
                    .append(BOS_MENU_NAME_EN)
                    .append(")");

            if (insertObjectList != null && insertObjectList.size() > 0) {
                int size = insertObjectList.size();
                sql.append(" values ");
                for (int i = 0; i < size; i++) {
                    MasBosMenu insertObj = insertObjectList.get(i);

                    //setup prepareStatement
                    sql.append(" ( ")
                            .append(" ? ").append(",")
                            .append(" ? ").append(",")
                            .append(" ? ").append(",");

                    sql
                            .append(" ? ").append(",")
                            .append(" ? ").append(",")
                            .append(" ? ").append(",")
                            .append(" ? ");

                    sql.append(" ) ");

                    if (i < size - 1) {
                        sql.append(",");
                    }

                    //setup object statement
                    parameters.add(insertObj.getCreateDate());
                    parameters.add(insertObj.getCreateBy());
                    parameters.add(insertObj.getIsDelete());

                    parameters.add(insertObj.getBosMenuCode());
                    parameters.add(insertObj.getBosMenuService());
                    parameters.add(insertObj.getBosMenuNameTH());
                    parameters.add(insertObj.getBosMenuNameEN());

                    sql.append(prepareObject.toString());

                    loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString(), CommonConstant.LOG_LEVEL_DEBUG);
                    loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()), CommonConstant.LOG_LEVEL_DEBUG);

                    jdbcTemplate.update(sql.toString(), parameters.toArray());

                }

            }
        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(TABLE, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;
        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(TABLE, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }

    }

    @Override
    public void update(MasBosMenu updateObject) throws Exception {
        ArrayList<Object> parameters = new ArrayList<>();
        StringBuilder sql = new StringBuilder();

        try {

            sql.append(" update ").append(TABLE).append(" SET ");

            if (updateObject != null) {
                sql
                        .append(UPDATE_DATE).append(" =  ? ").append(", ")
                        .append(UPDATE_BY).append(" =  ? ").append(", ")
                        .append(IS_DELETE).append(" =  ? ").append(", ");

                //update column 
                sql
                        .append(BOS_MENU_SERVICE).append(" = ? ").append(",")
                        .append(BOS_MENU_NAME_TH).append(" = ? ").append(",")
                        .append(BOS_MENU_NAME_EN).append(" = ? ");
                //update condition
                sql
                        .append(" WHERE ").append(BOS_MENU_CODE).append(" = ? ");

                parameters.add(updateObject.getUpdateDate());
                parameters.add(updateObject.getUpdateBy());
                parameters.add(updateObject.getIsDelete());

                //update column value
                parameters.add(updateObject.getBosMenuService());
                parameters.add(updateObject.getBosMenuNameTH());
                parameters.add(updateObject.getBosMenuNameEN());

                parameters.add(updateObject.getBosMenuCode());

            }

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString(), CommonConstant.LOG_LEVEL_DEBUG);
            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()), CommonConstant.LOG_LEVEL_DEBUG);

            jdbcTemplate.update(sql.toString(), parameters.toArray());

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(updateObject != null ? updateObject.getBosMenuCode() : null, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;

        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(updateObject != null ? updateObject.getBosMenuCode() : null, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;

        }
    }
}
