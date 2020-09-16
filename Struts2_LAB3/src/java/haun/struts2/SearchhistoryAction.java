/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.struts2;

import com.opensymphony.xwork2.ActionContext;
import haun.account.AccountDTO;
import haun.request.RequestDAO;
import haun.request.RequestDTO;
import java.util.List;
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
    @Result(name = "success", location = "history.jsp")
})
public class SearchhistoryAction {

    private String searchValue;
    private String date;
    private List<RequestDTO> historyList;
    private final String SUCCESS = "success";

    public SearchhistoryAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        AccountDTO user = (AccountDTO) session.get("USER");
        List<RequestDTO> tempList = RequestDAO.searchHistory(searchValue, date, user.getId());
        historyList = tempList;
        return SUCCESS;
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

    /**
     * @return the historyList
     */
    public List<RequestDTO> getHistoryList() {
        return historyList;
    }

    /**
     * @param historyList the historyList to set
     */
    public void setHistoryList(List<RequestDTO> historyList) {
        this.historyList = historyList;
    }

}
