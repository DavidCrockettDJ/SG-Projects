<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/tinymce/jquery.tinymce.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/tinymce/tinymce.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/AllStarBlog.js"></script>
        <link href="${pageContext.request.contextPath}/css/AllStarBlog.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/webApp.css" rel="stylesheet">
        <script>
            $(document).ready(function () {
                $('#pending').hide();
                $('#loginInfo').hide();

                if (${isLoggedIn} == true) {
                    $('#loginButton').hide();
                    $('#loginInfo').show();
                }
            });
        </script>
        <title>Bethesda</title>

        
        
        
    <nav id ="navIndex" class="navbar navbar-default">
        
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
                    <li><div id ="loginInfo" class="col-md-3">
                            <div class="dropdown">
                                <select name="accountChoice" class="dropbtn" onChange="window.location.href = this.value">
                                    <div class="dropdown-content">
                                        <option value="${pageContext.request.contextPath}">${userName}</option>
                                       <option value="${pageContext.request.contextPath}/static/account">Account</option>
                                        <option value="${pageContext.request.contextPath}/static/pendingList">Pending</option>
                                        <option value="${pageContext.request.contextPath}/static/addProfilePage">Create Profile</option>
                                        <option value="${pageContext.request.contextPath}/displayRemoveUserPage">Remove User</option>
                                        <option value="${pageContext.request.contextPath}/j_spring_security_logout">Log Out</option>
                                    </div>
                                </select>
                            </div>
                        </div>
                    </li>
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
                      action="submitStaticPost"
                      id="staticPost">

                    <div class="form-group">
                        <div class="col-md-9">
                            <textarea class = "tinymce" name="texteditor" form="staticPost"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="staticLink" placeholder="Enter the name of your link" required></input>
                    </div>
                    <div class="form-group">
                        <div class="col-md-9">
                            <button type="submit" name="addStaticPost" value="Add Post" class="btn btn-default">Add Page</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/AllStarBlog.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
