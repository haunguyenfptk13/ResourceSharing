<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<nav class="navbar navbar-expand-md">
    <div class="container-fluid">
        <a class="navbar-branch" href="#" style="margin-right: 30px; margin-left: 160px">
            <img src="images/fptlogo2.png" width="311" height="162" alt="fptlogo"/>
        </a>
        <div class="collapse navbar-collapse" style="margin-right: 160px;">
            <ul class="navbar-nav ml-auto">
                <s:if test="%{#session.USER != null}">
                    <s:if test="%{#session.USER.fullname != null}">
                        <li class="nav-item" style="margin: auto 10px">
                            Welcome, <font color="blue"><s:property value="%{#session.USER.fullname}" /></font>
                            <a href="logout" style="margin-left: 10px">Logout</a>
                        </li>
                    </s:if>
                    <s:else>
                        <li class="nav-item" style="margin: auto 10px">
                            Welcome, <font color="blue"><s:property value="%{#session.USER.email}" /></font>
                            <a href="logout" style="margin-left: 10px">Logout</a>
                        </li>
                    </s:else>
                </s:if>
                <s:if test="%{#session.USER == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">Login</a>
                    </li>
                </s:if>
                <li class="nav-item">
                    <a class="nav-link" href="search.jsp">Home</a>
                </li>
                <s:if test="%{#session.USER == null || #session.USER.roleID <= 2}">
                    <li class="nav-item">
                        <a class="nav-link" href="createNewAccount.jsp">Register</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="history.jsp">History</a>
                    </li>
                </s:if>
                <s:if test="%{#session.USER.roleID == 3}">
                    <li class="nav-item">
                        <a class="nav-link" href="adminSearch.jsp">Request Manager</a>
                    </li>
                </s:if>
            </ul>
        </div>  
    </div>
</nav>
