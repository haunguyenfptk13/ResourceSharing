/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.struts2;

import com.opensymphony.xwork2.ActionContext;
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
    @Result(name = "success", location = "adminSearch.jsp")
})
public class SearchrequestAction {

    private String searchValue;
    private String role;
    private String statusReq;
    private String date;
    private List<RequestDTO> requestList;
    private int pageNo;
    private final String SUCCESS = "success";

    public SearchrequestAction() {
    }

    public String execute() throws Exception {
        int recordTotal = RequestDAO.getSizeResultSearch(searchValue, statusReq, date, role);
        int recordPage = 5;
        int pageNumber = recordTotal / recordPage;
        if (recordTotal > (pageNumber * recordPage)) {
            pageNumber += 1;
        }

        if (pageNo > pageNumber && pageNo > 1) {
            pageNo -= 1;
            setPageNo(pageNo);
        }

        int recordOffset = (getPageNo() - 1) * recordPage;
        List<RequestDTO> tempList = RequestDAO.getDataSearchResult(searchValue, statusReq, date, role, recordOffset, recordPage);
        requestList = tempList;
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("PAGENUMBER", pageNumber);
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
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the statusReq
     */
    public String getStatusReq() {
        return statusReq;
    }

    /**
     * @param statusReq the statusReq to set
     */
    public void setStatusReq(String statusReq) {
        this.statusReq = statusReq;
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
     * @return the requestList
     */
    public List<RequestDTO> getRequestList() {
        return requestList;
    }

    /**
     * @param requestList the requestList to set
     */
    public void setRequestList(List<RequestDTO> requestList) {
        this.requestList = requestList;
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

}
