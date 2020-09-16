<%-- 
    Document   : createNewAccount
    Created on : Jul 8, 2020, 11:50:06 PM
    Author     : msi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account Page</title>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <s:head/>
    </head>
    <body>

        <div id="signupbox" style="margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">Sign Up</div>
                    <div style="float:right; font-size: 85%; position: relative; top:-10px"><a href="login.jsp">Sign In</a></div>
                </div>  
                <div class="panel-body" >
                    <form action="createAccount" class="form-horizontal" role="form" method="POST">

                        <div id="signupalert" style="display:none" class="alert alert-danger">
                            <p>Error:</p>
                            <span></span>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Username</label>
                            <div class="col-md-9">
                                <s:textfield name="username" cssClass="form-control" placeholder="Username"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Password</label>
                            <div class="col-md-9">
                                <s:password name="password" cssClass="form-control" placeholder="Password" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Confirm</label>
                            <div class="col-md-9">
                                <s:password name="confirm" cssClass="form-control" placeholder="Confirm Password" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Email</label>
                            <div class="col-md-9">
                                <s:textfield name="email" cssClass="form-control" placeholder="Email"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Name</label>
                            <div class="col-md-9">
                                <s:textfield name="name" cssClass="form-control" placeholder="Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="icode" class="col-md-3 control-label">Phone</label>
                            <div class="col-md-9">
                                <s:textfield name="phone" cssClass="form-control" placeholder="Phone"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Address</label>
                            <div class="col-md-9">
                                <s:textfield name="address" cssClass="form-control" placeholder="Address"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <!-- Button -->                                        
                            <div class="col-md-offset-3 col-md-9 text-right">
                                <button type="submit" class="btn btn-info"> Sign Up</button>  
                            </div>
                        </div>

                    </form>
                    <s:if test="%{exception.message.contains('duplicate')}">
                        <b>
                            <font color="red">
                            <s:property value="%{username}"/> already exists
                            </font>
                        </b>
                    </s:if>
                </div>
            </div>




        </div> 
    </body>
</html>
