package com.app.atmsimulation.controller;

import javax.servlet.http.HttpSession;

public interface BaseController {

    boolean authenticateAccount(HttpSession httpSession);
}
