package com.sample.user.dto;

/**
 * Created by andongxu on 16-11-16.
 */
public class LoginReqDto {

    private String accountNo;

    private String password;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
