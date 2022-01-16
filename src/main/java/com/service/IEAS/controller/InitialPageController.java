/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.controller;

import com.service.IEAS.constant.CommonConstant;
import com.service.IEAS.dao.MasBosMenuDao;
import com.service.IEAS.dao.PckUserDao;
import com.service.IEAS.domain.MasBosMenu;
import com.service.IEAS.domain.UserAuthentication;
import com.service.IEAS.model.rest.response.GetMenuResponse;
import com.service.IEAS.model.rest.response.ListMenu;
import com.service.IEAS.service.LoggerService;
import com.service.IEAS.util.StringUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author tanyong
 */
@Controller
public class InitialPageController {

    @Autowired
    private LoggerService logger;

    @Autowired
    private MasBosMenuDao masBosMenuDao;

    @Autowired
    private PckUserDao pckUserDao;

//    @Autowired
//    private MasSysParameterDao masSysParameterDao;
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Authentication authentication, Model model) throws Exception {
        Date startDate = new Date();

        model.addAttribute("application", "BOS");
        model.addAttribute("message", "Welcome " + authentication.getName());
        model.addAttribute("authority", authentication.getAuthorities());

        logger.accessLogger(startDate, new Date(), "Application_Details", "Succeed.", null, null, CommonConstant.LOG_LEVEL_INFO);
        return "main";
    }

    @ModelAttribute("getMenu")
    public GetMenuResponse getMenu(Authentication authentication, Model model, HttpServletRequest request, HttpServletResponse res) throws UsernameNotFoundException, Exception {
        GetMenuResponse response = new GetMenuResponse();
        List<ListMenu> listMenus = new ArrayList<>();
        Date currentDate = new Date();
        Date expireDate;
        StringBuilder sb = new StringBuilder();
        try {

            UserAuthentication userlogin = new UserAuthentication();
            userlogin.setUsername(authentication.getName());
            List<UserAuthentication> authenticationsListDB = pckUserDao.find(userlogin, null);
            UserAuthentication authenticationDB = authenticationsListDB != null && authenticationsListDB.size() > 0 ? authenticationsListDB.get(0) : null;

            if (authenticationDB != null) {
//                MasSysParameter sysParameter = new MasSysParameter();
//                sysParameter.setIsDelete(CommonConstant.FLAG_NO);
//                sysParameter.setParameterKey(CommonConstant.USER_LOGIN_EXPIRE);
//                List<MasSysParameter> masSysParameterslistDB = masSysParameterDao.find(sysParameter, null);
//                MasSysParameter masSysParameter = masSysParameterslistDB != null && masSysParameterslistDB.size() > 0 ? masSysParameterslistDB.get(0) : null;
//                if (masSysParameter != null) {
                String month = "6";
                if (authenticationDB.getUpdateJackDate() != null) {

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(authenticationDB.getUpdateJackDate());
                    calendar.add(Calendar.MONTH, Integer.valueOf(month));
                    expireDate = calendar.getTime();
                    if (currentDate.compareTo(expireDate) == 1) {
                        //pwd expire
                        throw new CredentialsExpiredException("pwd expire please change your pwd");
                    }
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(authenticationDB.getCreateDate());
                    calendar.add(Calendar.MONTH, Integer.valueOf(month));
                    expireDate = calendar.getTime();
                    if (currentDate.compareTo(expireDate) == 1) {
                        //pwd expire
                        throw new CredentialsExpiredException("pwd expire please change your pwd");
                    }
                }
//                }
            }

            if (authenticationDB != null && StringUtils.isNotEmptyOrNull(authenticationDB.getPicture())) {
                response.setName(authenticationDB.getFirstName() + " " + authenticationDB.getLastName());
                String[] imageArray = authenticationDB.getPicture().split("/");
                String image = imageArray[imageArray.length - 1];
            }

            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {

                MasBosMenu masBosMenuFinder = new MasBosMenu();
                masBosMenuFinder.setIsDelete(CommonConstant.FLAG_N);
                masBosMenuFinder.setBosMenuCode(grantedAuthority.getAuthority());
                List<MasBosMenu> masBosMenuListDB = masBosMenuDao.find(masBosMenuFinder);

                if (masBosMenuListDB != null) {
                    for (MasBosMenu masBosMenu : masBosMenuListDB) {
                        ListMenu listMenu = new ListMenu();
                        listMenu.setBosMenuNameEN(masBosMenu.getBosMenuNameEN());
                        listMenu.setBosMenuNameTH(masBosMenu.getBosMenuNameTH());
                        listMenu.setBosMenuService(masBosMenu.getBosMenuService());
                        listMenus.add(listMenu);
                    }
                }

            }
            response.setListMenus(listMenus);

        } catch (AuthenticationException e) {
            logger.printStackTraceToErrorLog(request.getSession().getId(), e.getClass().toString(), e);
            sb
                    .append("/").append("loginExpire");
            logger.systemLogger(null, "Authentication failure.", request.getSession().getId(), CommonConstant.LOG_LEVEL_INFO);

            res.sendRedirect(sb.toString());
            return response;

        } catch (Exception e) {
            logger.printStackTraceToErrorLog(request.getSession().getId(), e.getClass().getSimpleName(), e);
            throw e;
        }

        return response;
    }
    
    @RequestMapping("/initMainReportManagement")
    @PreAuthorize("hasAuthority('1')")
    public String initAuthorizeManageMent(Model model) {
        return "mainReportManagement";
    }
}
