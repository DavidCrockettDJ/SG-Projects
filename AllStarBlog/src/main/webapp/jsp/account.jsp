<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <link href="${pageContext.request.contextPath}/css/AllStarBlog.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/webApp.css" rel="stylesheet">
        <script>
            $(document).ready(function () {
                $('#loginInfo').hide();

                if (${isLoggedIn} == true) {
                    $('#loginButton').hide();
                    $('#loginInfo').show();
                }

            });


        </script>
        <script>
            $(function () {
                $("#datepicker").datepicker();
            });
        </script>
        <script>
            document.getElementById('accountChoice').onchange = function () {
                window.location.href = this.children[this.selectedIndex].getAttribute('href');
            }
        </script>
        <title>Bethesda</title>
        
        
        <!--    Navbar-->
    <nav id="navIndex" class="navbar navbar-default">
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
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li><div id ="loginInfo" class="col-md-3">
                            <div class="dropdown">
                                <select name="accountChoice" class="dropbtn" onChange="window.location.href = this.value">
                                    <div class="dropdown-content">
                                        <option value="${pageContext.request.contextPath}">${userName}</option>
                                        <option value="static/account">Account</option>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <option value="pendingList">Pending</option>
                                        <option value="${pageContext.request.contextPath}/static/addProfilePage">Create Profile</option>
                                        <option value="${pageContext.request.contextPath}/displayRemoveUserPage">Remove User</option>
                                        </sec:authorize>
                                        <option value="${pageContext.request.contextPath}/j_spring_security_logout">Log Out</option>
                                    </div>
                                </select>
                            </div>
                        </div>
                    </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</head>
<body>
    <div class="container">
        <div class="col-md-12">
            <div class="col-md-offset-2">
                <div class="form-group" style="margin-left: 150px;">
                    <label for="firstName">First Name: </label>
                    <input type="text" class="form-control" name="firstName" id="firstName" style="width: 200px;" value="${profile.firstName}" required>
                </div>
            </div>
                <div class="col-md-offset-2">
                <div class="form-group" style="margin-left: 150px;">
                    <label for="firstName">Last Name: </label>
                    <input type="text" class="form-control" name="lastName" id="lastName" style="width: 200px;" value="${profile.lastName}" required>
                </div>
            </div>
                <div class="col-md-offset-2">
                <div class="form-group" style="margin-left: 150px;">
                    <label for="datepicker">Birthday: </label>
                    <input id="datepicker" class="form-control" name="datepicker" style="width: 100px;" value="${profile.birthday}" readonly>
                </div>
            </div>
                <div class="row">
                <div class="col-md-offset-2">
                    <div class="form-group">
                        <a href="saveAccount"><input type="submit" class="btn btn-default" value="Save"></input></a>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a style="margin-left: 400px; margin-bottom: 100px;" href="addProfilePage"><button class="btn btn-default">Add New User</button></a>
                        </sec:authorize>
                    </div>
                </div>
                </div>
        </div>
    </div>
</body>
</html>
