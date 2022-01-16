/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.controller;

import com.service.IEAS.constant.CommonConstant;
import com.service.IEAS.service.LoggerService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.net.ssl.HttpsURLConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tanyong
 */
@RestController
public class ApplicationController {

    @Value("${bulid.enviromment}")
    private String env;

    @Value("${bulid.name}")
    private String appName;

    @Value("${bulid.version}")
    private String appVersion;

    @Value("${bulid.timestamp}")
    private String appTimestamp;

    @Autowired
    private LoggerService logger;

    @GetMapping("/")
    @ResponseBody
    public String getVersion() throws Exception {
        Date startDate = new Date();
        StringBuilder sb = new StringBuilder();

//        String result = rawDatabaseConnection("jdbc:oracle:thin:@172.30.123.7:1521/RESUAT",
//                "root", "p@ssw0rd", "SELECT * FROM MFEC_USER.PCK_USER_LOGIN");

        sb
                .append(" Name: ").append(appName).append(",")
                .append(" Environment: ").append(env).append(",")
                .append(" Version: ").append(appVersion);

        logger.accessLogger(startDate, new Date(), "Application_Details", "Succeed.", null,
                sb.toString(), CommonConstant.LOG_LEVEL_INFO);
        return sb.toString();
    }

    public static String getStatus(String url) throws Exception {

        String result = "";
        int code = 200;
        try {
            URL siteURL = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.connect();

            code = connection.getResponseCode();
            if (code == 200) {
                result = "-> Green <-\t" + "Code: " + code;
            } else {
                result = "-> Yellow <-\t" + "Code: " + code;
            }
        } catch (IOException e) {
            result = "-> Red <-\t" + "Wrong domain - Exception: " + e.getMessage();
        }
        System.out.println(url + "\t\tStatus:" + result);
        return result;
    }

    private static String rawDatabaseConnection(String databaseUrl, String databaseUsername, String databasePassword,
            String query) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (Connection conn = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(query)) {
                    int i = 1;
                    while (rs.next()) {
                        sb.append("########## Result: " + i + " ##########");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }

        } catch (Exception e) {
            throw e;

        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        try {
            rawDatabaseConnection("jdbc:mysql://localhost:3306/income_expense_account_service",
                    "root", "P@ssw0rd", "SELECT * FROM pet");
        } catch (Exception e) {
            throw e;
        }
    }
}
