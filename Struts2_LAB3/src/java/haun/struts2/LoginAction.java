/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.struts2;

import com.opensymphony.xwork2.ActionContext;
import haun.account.AccountDAO;
import haun.account.AccountDTO;
import haun.utils.APIWrapper;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

/**
 *
 * @author msi
 */
@ResultPath("/")
@Results({
    @Result(name = "success", location = "search.jsp")
    , 
    @Result(name = "fail", type = "redirect", location = "invalid.html")
})
public class LoginAction {

    private String username;
    private String password;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public LoginAction() {
    }

    public String execute() throws Exception {
        String code = ServletActionContext.getRequest().getParameter("g-recaptcha-response");
        //call DAO
        AccountDTO userInfo = AccountDAO.checkLogin(username, password);
        APIWrapper wrapper = new APIWrapper();
        boolean verify = wrapper.verifyReCAPTCHA(code);
        //process
        String url = FAIL;
        if ((userInfo != null) && verify) {
            Map session = ActionContext.getContext().getSession();
            session.put("USER", userInfo);

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

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
