/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.struts2;

import com.opensymphony.xwork2.ActionContext;
import haun.account.AccountDTO;
import haun.request.RequestDAO;
import java.time.LocalDate;
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
    @Result(name = "success", type = "redirectAction",
            params = {
                "actionName", "search",
                "searchValue", "${lastSearchValue}",
                "dateTo", "${datePay}",
                "dateFrom", "${dateBorrow}",
                "pageNo", "${pageNo}",
                "category", "${category}"
            })
    ,
    @Result(name = "fail", location = "insertErr.html")
})
public class RequestAction {

    private String resourceID;
    private int remainQuantity;
    private int remainQuantityMAX;
    private String lastSearchValue;
    private String dateBorrow;
    private String datePay;
    private String category;
    private int pageNo;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public RequestAction() {
    }

    public String execute() throws Exception {
        //get User Info
        Map session = ActionContext.getContext().getSession();
        AccountDTO userInfo = (AccountDTO) session.get("USER");
        int id = userInfo.getId();
        //get Date
        String localDate = LocalDate.now().toString();
        //call DAO to insert request
        boolean update = (getRemainQuantity() <= getRemainQuantityMAX());
        String url = FAIL;
        if(remainQuantity == 0){
            update = false;
        }
        if (update) {
            boolean result = RequestDAO.insertNewRequest(resourceID, getRemainQuantity(), dateBorrow, datePay, 1, localDate, id);
            if (result) {
                url = SUCCESS;
            }
        }
        return SUCCESS;
    }

    /**
     * @return the resourceID
     */
    public String getResourceID() {
        return resourceID;
    }

    /**
     * @param resourceID the resourceID to set
     */
    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    /**
     * @return the lastSearchValue
     */
    public String getLastSearchValue() {
        return lastSearchValue;
    }

    /**
     * @param lastSearchValue the lastSearchValue to set
     */
    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }

    /**
     * @return the dateBorrow
     */
    public String getDateBorrow() {
        return dateBorrow;
    }

    /**
     * @param dateBorrow the dateBorrow to set
     */
    public void setDateBorrow(String dateBorrow) {
        this.dateBorrow = dateBorrow;
    }

    /**
     * @return the datePay
     */
    public String getDatePay() {
        return datePay;
    }

    /**
     * @param datePay the datePay to set
     */
    public void setDatePay(String datePay) {
        this.datePay = datePay;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the pageNo
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo the pageNo to set
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @return the remainQuantity
     */
    public int getRemainQuantity() {
        return remainQuantity;
    }

    /**
     * @param remainQuantity the remainQuantity to set
     */
    public void setRemainQuantity(int remainQuantity) {
        this.remainQuantity = remainQuantity;
    }

    /**
     * @return the remainQuantityMAX
     */
    public int getRemainQuantityMAX() {
        return remainQuantityMAX;
    }

    /**
     * @param remainQuantityMAX the remainQuantityMAX to set
     */
    public void setRemainQuantityMAX(int remainQuantityMAX) {
        this.remainQuantityMAX = remainQuantityMAX;
    }

}
