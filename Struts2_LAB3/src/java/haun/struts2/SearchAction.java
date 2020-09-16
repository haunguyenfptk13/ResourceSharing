/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.struts2;

import com.opensymphony.xwork2.ActionContext;
import haun.account.AccountDTO;
import haun.resource.ResourceDAO;
import haun.resource.ResourceDTO;
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
@Results(
        @Result(name = "success", location = "search.jsp")
)
public class SearchAction {

    private String searchValue;
    private String category;
    private String dateTo;
    private String dateFrom;
    private int pageNo;
    private List<ResourceDTO> listResources;
    private final String SUCCESS = "success";

    public SearchAction() {
    }

    public String execute() throws Exception {
        //get Role of user
        Map session = ActionContext.getContext().getSession();
        AccountDTO user = (AccountDTO) session.get("USER");
        int roleID;
        if (user != null) {
            roleID = user.getRoleID();
        } else {
            roleID = 2;
        }
        int recordTotal = ResourceDAO.getSizeResultSearch2(searchValue, category, dateFrom, dateTo, roleID);
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
        //call DAO
        List<ResourceDTO> tempList = ResourceDAO.getPageDataSearch2(searchValue, category, dateFrom, dateTo, roleID, recordOffset, recordPage);
        listResources = tempList;
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
     * @return the listResources
     */
    public List<ResourceDTO> getListResources() {
        return listResources;
    }

    /**
     * @param listResources the listResources to set
     */
    public void setListResources(List<ResourceDTO> listResources) {
        this.listResources = listResources;
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
     * @return the dateTo
     */
    public String getDateTo() {
        return dateTo;
    }

    /**
     * @param dateTo the dateTo to set
     */
    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * @return the dateFrom
     */
    public String getDateFrom() {
        return dateFrom;
    }

    /**
     * @param dateFrom the dateFrom to set
     */
    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
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
