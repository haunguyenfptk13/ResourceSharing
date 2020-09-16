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
    @Result(name = "fail", location = "invalid.html", type = "redirect")
})
public class LogingoogleAction {

    private String code;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public LogingoogleAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        //code -> accessToken
        APIWrapper wrapper = new APIWrapper();
        String accessToken = wrapper.getAccessToken(code);
        wrapper.setAccessToken(accessToken);

        //access userInfo
        AccountDTO userInfo = wrapper.getUserInfo();
        //check exist
        boolean userExist = (AccountDAO.checkLogin(userInfo.getGoogleID()) != null);
        //if not exist -> register
        Map session = ActionContext.getContext().getSession();
        if (!userExist) {
            boolean result = AccountDAO.register(userInfo.getEmail(), userInfo.getGoogleID());
            //process result
            if (result) {
                AccountDTO user = AccountDAO.checkLogin(userInfo.getGoogleID());
                session.put("USER", user);
                url = SUCCESS;
            }
        } else {
            AccountDTO user = AccountDAO.checkLogin(userInfo.getGoogleID());
            session.put("USER", user);
            url = SUCCESS;
        }
        return url;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

}
