<%-- 
    Document   : search
    Created on : Jul 7, 2020, 5:16:37 PM
    Author     : msi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link rel="stylesheet" href="css/style.css" >
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    </head>
    <body>

        <s:include value="navbar.jsp" />
        <div class="container">
            <form action="search">
                <input type="text" name="searchValue" value="<s:property value="%{searchValue}"/>" placeholder="Input Value"/>
                <label style="margin: 0 10px 0 25px; font-size: 18px; font-weight: normal">Category*</label>
                <select name="category">
                    <s:iterator var="dto" value="%{#session.CATELIST}">
                        <s:if test="%{#dto.categoryName == category}">
                            <option selected="selected"><s:property value="%{#dto.categoryName}"/></option>
                        </s:if>
                        <s:if test="%{#dto.categoryName != category}">
                            <option><s:property value="%{#dto.categoryName}"/></option>
                        </s:if>
                    </s:iterator>
                </select>
                <label style="margin: 0 10px 0 25px; font-size: 18px; font-weight: normal">Date From*</label><input type="date" name="dateFrom" value="<s:property value="%{dateFrom}"/>" />
                <label style="margin: 0 10px 0 25px; font-size: 18px; font-weight: normal">Date To*</label><input type="date" name="dateTo" value="<s:property value="%{dateTo}"/>" />
                <input type="submit" value="Search" style="margin-left: 15px; font-size: 18px" class="btn btn-primary"/>
                <s:hidden name="pageNo" value="1" />
            </form>
        </div>

        <div class="container" style="margin-top: 25px">
            <s:if test="%{searchValue != null and searchValue != ''}">
                <s:if test="%{listResources != null}">
                    <table border="1" class="table table-primary table-hover table-borderless text-center">
                        <thead class="thead-dark">
                            <tr>
                                <th>Resource ID</th>
                                <th>Resource Name</th>
                                <th>Color</th>
                                <th>Category ID</th>
                                <th>Quantity</th>
                                <th>Role ID</th>
                                <th>Remain Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator var="dto" value="listResources" status="counter">
                            <form action="request">
                                <tr>
                                    <td>
                                        <s:property value="%{#dto.resourceID}" />
                                        <s:hidden name="resourceID" value="%{#dto.resourceID}" />
                                    </td>
                                    <td>
                                        <s:property value="%{#dto.resourceName}" />
                                    </td>
                                    <td>
                                        <s:property value="%{#dto.color}" />
                                    </td>
                                    <td>
                                        <s:property value="%{#dto.categoryID}" />
                                    </td>                        
                                    <td>
                                        <s:property value="%{#dto.quantity}" />
                                    </td>
                                    <td>
                                        <s:property value="%{#dto.roleID}" />
                                    </td>
                                    <td>
                                        <input type="text" name="remainQuantity" value="<s:property value="%{#dto.quantity - #dto.amount}" />" />
                                        <s:hidden name="remainQuantityMAX" value="%{#dto.quantity - #dto.amount}"/>
                                        <s:if test="%{(#dto.quantity - #dto.amount) == 0}">
                                            <font color="red">Out of stock</font>
                                        </s:if>
                                    </td>
                                    <td>
                                        <input type="submit" value="Request" class="btn btn-primary"/>
                                        <s:hidden name="lastSearchValue" value="%{searchValue}" />
                                        <s:hidden name="dateBorrow" value="%{dateFrom}" />
                                        <s:hidden name="datePay" value="%{dateTo}" />
                                        <s:hidden name="category" value="%{category}" />
                                        <s:hidden name="pageNo" value="%{pageNo}"/>
                                    </td> 
                                </tr>
                            </form>
                        </s:iterator>
                        </tbody>
                    </table>
                </s:if>
                <div class="container text-center" style="margin-bottom: 20px; margin-top: 20px">
                    <s:if test="%{#request.PAGENUMBER > 1}">
                        <s:iterator begin="1" end="%{#request.PAGENUMBER}" var="i">
                            <s:url var="pageLink" value="search">
                                <s:param name="searchValue" value="%{searchValue}"/>
                                <s:param name="category" value="%{category}"/>
                                <s:param name="dateTo" value="%{dateTo}"/>
                                <s:param name="dateFrom" value="%{dateFrom}"/>
                                <s:param name="pageNo" value="%{#i}"/>
                            </s:url>
                            <s:a href="%{pageLink}"><s:property value="%{#i}" /></s:a>
                        </s:iterator>
                    </s:if>
                </div>
                <s:if test="%{exception.message.contains('CHECK')}">
                    <b>
                        <font color="red">
                        <s:property value="%{resourceID}"/> not enough quantity
                        </font>
                    </b>
                </s:if>
            </s:if>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>
</html>
