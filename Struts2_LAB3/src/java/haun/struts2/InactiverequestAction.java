/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.struts2;

import haun.request.RequestDAO;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

/**
 *
 * @author msi
 */
@ResultPath("/")
@Results({
    @Result(name = "success", type = "redirectAction",
            params = {
                "actionName", "searchhistory",
                "searchValue", "${searchValue}",
                "date", "${date}"
            })
    , 
    @Result(name = "fail", location = "insertErr.html")
})
public class InactiverequestAction {

    private int pk;
    private String searchValue;
    private String date;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public InactiverequestAction() {
    }

    public String execute() throws Exception {
        boolean result = RequestDAO.inactiveRequest(pk);
        String url = FAIL;
        if(result){
            url = SUCCESS;
        }
        return url;
    }

    /**
     * @return the pk
     */
    public int getPk() {
        return pk;
    }

    /**
     * @param pk the pk to set
     */
    public void setPk(int pk) {
        this.pk = pk;
    }

    /**
     * @return the searchValue
     */
    public String getSearchValue() {
        return searchValue;
    }

    /**
     * @param searchValue the searchValue to set
     */
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

}
