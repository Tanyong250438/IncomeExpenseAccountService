/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS.controller;

import com.service.IEAS.service.LoggerService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author tanyong
 */
@Controller
public class LoginController {

    @Autowired
    private LoggerService logger;
    
    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        return "logout";
    }

    //Error Login : Control by spring security
    @RequestMapping("/loginException")
    public String loginException(Model model, HttpServletRequest request) {

        if (request != null) {
            model.addAttribute("exception", request.getParameter("exception"));
            model.addAttribute("exceptionCode", request.getParameter("exceptionCode"));
            model.addAttribute("exceptionMessage", request.getParameter("exceptionMessage"));

        }

        return "login";
    }

//    @RequestMapping("/sessionExpired")
//    public String sessionExpired(Model model) {
//        String exceptionCode = HttpStatus.REQUEST_TIMEOUT.toString();
//        String exceptionCodeMessage = messageCodeService.getMessageByMessageCode(exceptionCode);
//
//        model.addAttribute("exception", true);
//        model.addAttribute("exceptionCode", exceptionCode);
//        model.addAttribute("exceptionMessage", exceptionCodeMessage);
//
//        return "login";
//    }
//
//    @RequestMapping("/accessDenied")
//    public String accessDenied(Model model) {
//        String exceptionCode = HttpStatus.FORBIDDEN.toString();
//        String exceptionCodeMessage = messageCodeService.getMessageByMessageCode(exceptionCode);
//
//        model.addAttribute("exceptionCode", exceptionCode);
//        model.addAttribute("exceptionMessage", exceptionCodeMessage);
//
//        return "error";
//    }

    @RequestMapping("/loginExpire")
    public String loginExpire(Model model) {
        return "loginExpire";
    }

}
