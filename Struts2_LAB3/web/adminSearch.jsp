<%-- 
    Document   : adminSearch
    Created on : Jul 18, 2020, 4:30:13 PM
    Author     : msi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Request Process Page</title>
        <link rel="stylesheet" href="css/style.css" >
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    </head>
    <body>
        <s:include value="navbar.jsp" />

        <div class="container">
            <form action="searchrequest">
                <input type="text" name="searchValue" value="<s:property value="%{searchValue}"/>" placeholder="Input Value"/>
                <label style="margin: 0 10px 0 25px; font-size: 18px; font-weight: normal">User Request*</label>
                <select name="role">
                    <s:iterator var="dto" value="%{#session.ROLELIST}">
                        <s:if test="%{#dto.roleName == role}">
                            <option selected="selected"><s:property value="%{#dto.roleName}"/></option>
                        </s:if>
                        <s:if test="%{#dto.roleName != role}">
                            <option><s:property value="%{#dto.roleName}"/></option>
                        </s:if>
                    </s:iterator>
                </select>
                <label style="margin: 0 10px 0 25px; font-size: 18px; font-weight: normal">Status Request*</label>
                <select name="statusReq">
                    <s:iterator var="dto" value="%{#session.STATUSREQLIST}">
                        <s:if test="%{#dto.statusName == statusReq}">
                            <option selected="selected"><s:property value="%{#dto.statusName}"/></option>
                        </s:if>
                        <s:if test="%{#dto.statusName != statusReq}">
                            <option><s:property value="%{#dto.statusName}"/></option>
                        </s:if>
                    </s:iterator>
                </select>
                <label style="margin: 0 10px 0 25px; font-size: 18px; font-weight: normal">Date*</label><input type="date" name="date" value="<s:property value="%{date}"/>" />
                <input type="submit" value="Search" style="margin-left: 15px; font-size: 18px" class="btn btn-primary"/>
                <s:hidden name="pageNo" value="1" />
            </form>
        </div>

        <div class="container" style="margin-top: 25px">
            <s:if test="%{searchValue != null and searchValue != ''}">
                <s:if test="%{requestList != null}">
                    <table border="1" class="table table-primary table-hover table-borderless text-center">
                        <thead class="thead-dark">
                            <tr>
                                <th>Request ID</th>
                                <th>Resource ID</th>
                                <th>Amount</th>
                                <th>Borrow Date</th>
                                <th>Pay Date</th>
                                <th>Status ID</th>
                                <th>Create Date</th>
                                <th>Account ID</th>
                                <th>Action1</th>
                                <th>Action2</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator var="dto" value="requestList" status="counter">
                            <form action="acceptrequest">
                                <tr>
                                    <td>
                                        <s:property value="%{#dto.requestID}" />
                                        <s:hidden name="pk" value="%{#dto.requestID}" />
                                    </td>
                                    <td>
                                        <s:property value="%{#dto.resourceID}" />
                                    </td>
                                    <td>
                                        <s:property value="%{#dto.amount}" />
                                    </td>
                                    <td>
                                        <s:property value="%{#dto.borrowDate}" />
                                    </td>                        
                                    <td>
                                        <s:property value="%{#dto.payDate}" />
                                    </td>
                                    <td>
                                        <s:property value="%{#dto.statusID}" />
                                    </td>
                                    <td>
                                        <s:property value="%{#dto.createDate}" />
                                    </td>
                                    <td>
                                        <s:property value="%{#dto.accountID}" />
                                    </td>
                                    <td>
                                        <s:url var="deleteLink" value="deleterequest">
                                            <s:param name="pk" value="%{#dto.requestID}"/>
                                            <s:param name="searchValue" value="%{searchValue}"/>
                                            <s:param name="role" value="%{role}"/>
                                            <s:param name="statusReq" value="%{statusReq}"/>
                                            <s:param name="date" value="%{date}"/>
                                            <s:param name="pageNo" value="%{pageNo}"/>
                                        </s:url>
                                        <s:a href="%{deleteLink}">Delete</s:a>
                                        </td>
                                        <td>
                                            <input type="submit" value="Accept" class="btn btn-primary"/>
                                        <s:hidden name="searchValue" value="%{searchValue}" />
                                        <s:hidden name="role" value="%{role}"/>
                                        <s:hidden name="statusReq" value="%{statusReq}"/>
                                        <s:hidden name="date" value="%{date}"/>
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
                            <s:url var="pageLink" value="searchrequest">
                                <s:param name="searchValue" value="%{searchValue}"/>
                                <s:param name="role" value="%{role}"/>
                                <s:param name="statusReq" value="%{statusReq}"/>
                                <s:param name="date" value="%{date}"/>
                                <s:param name="pageNo" value="%{#i}"/>
                            </s:url>
                            <s:a href="%{pageLink}"><s:property value="%{#i}" /></s:a>
                        </s:iterator>
                    </s:if>
                </div>
            </s:if>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>
</html>
