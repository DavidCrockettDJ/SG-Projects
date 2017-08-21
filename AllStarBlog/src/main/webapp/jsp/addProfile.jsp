<%-- 
    Document   : addPost
    Created on : Jul 21, 2017, 9:56:01 AM
    Author     : Becca
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <link href="${pageContext.request.contextPath}/css/AllStarBlog.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <script>
            $(function () {
                $("#datepicker").datepicker();
            });
        </script>
        
    <nav id="navIndex" class="navbar navbar-default">
         <c:if test="${pageContext.request.userPrincipal.name != null}">
            <p>Hello : ${pageContext.request.userPrincipal.name}
              | <a href="${pageContext.request.contextPath}/j_spring_security_logout" />Logout</a>
            </p>
        </c:if>
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}">Bethesda</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN, ROLE_USER')">
                    <li><a href="${pageContext.request.contextPath}/addPost">Add Blog Post</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/static/addStaticPost">Add Page</a></li>
                    </sec:authorize>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <!--                   USERPIC WILL APPEAR HERE-->
                </ul>
            </div>
        </div>
    </nav>
</head>
<body>
    <div class="container">

        <div class="col-md-12">
            <div class ="col-md-offset-2 col-md-8">
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="createProfile"
                      id="addProfile">
                    <div class="form-group">
                        <label for="userName" class="col-md-3">User Name:</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="userName" placeholder="User Name" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-3">Password:</label>
                        <div class="col-md-9">
                            <input type="password" class="form-control" name="password" placeholder="Password" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstName" class="col-md-3">First Name:</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="firstName" placeholder="First Name" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastName" class="col-md-3">Last Name:</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="lastName" placeholder="Last Name" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-md-3">Email:</label>
                        <div class="col-md-9">
                            <input type="email" class="form-control" name="email" placeholder="Email" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="datepicker" class="col-md-3">Birthday:</label>
                        <div class="col-md-9">
                            <input id="datepicker" class="form-control" name="datepicker" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-9">
                            <button type="submit" name="login" value="Login" class="btn btn-default">Create Login</button>
                        </div>
                    </div>

                    </body>
                    </html>
