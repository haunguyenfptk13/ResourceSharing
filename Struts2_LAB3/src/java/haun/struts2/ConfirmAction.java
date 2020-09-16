/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.struts2;

import haun.account.AccountDAO;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

/**
 *
 * @author msi
 */
@ResultPath("/")
@Results({
    @Result(name = "success", location = "login.jsp")
    ,
    @Result(name = "fail", type = "redirect", location = "createNewAccount.jsp")
})
public class ConfirmAction {

    private String username;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public ConfirmAction() {
    }

    public String execute() throws Exception {
        boolean result = AccountDAO.activeAccount(username);
        String url = FAIL;
        if(result){
            url = SUCCESS;
        }
        return url;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }


}
