package com.regino.service;

import com.regino.dao.LoginDao;

public class LoginService {
    LoginDao loginDao = new LoginDao();

    public boolean findById(String username, String password) {
        return loginDao.findById(username, password);
    }
}
