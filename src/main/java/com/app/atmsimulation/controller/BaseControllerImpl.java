package com.app.atmsimulation.controller;

import javax.servlet.http.HttpSession;

public class BaseControllerImpl implements BaseController {

    @Override
    public boolean authenticateAccount(HttpSession httpSession) {

        if (httpSession.getAttribute("account") == null) {
            return false;
        }

        return true;
    }
}
