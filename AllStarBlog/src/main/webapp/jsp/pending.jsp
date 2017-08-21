<%-- 
    Document   : addPost
    Created on : Jul 21, 2017, 9:56:01 AM
    Author     : Becca
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head> 
        <title>Bethesda</title>

        <style>
            #loginInfo{
                color:white;
            }
        </style>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/AllStarBlog.js"></script>
        <link href="${pageContext.request.contextPath}/css/AllStarBlog.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/bootstrap-tagsinput.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/webApp.css" rel="stylesheet">
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
                <ul  class="nav navbar-nav navbar-right">
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
            <c:forEach var="blogPost" items="${blogList}">
                <div class ="col-md-12">
                    <div class ="col-md-offset-2 col-md-8">
                        <c:out value="${blogPost.profile.firstName}"/>
                    </div>
                    <div class ="col-md-offset-2 col-md-8">
                        ${blogPost.description}
                    </div>
                    <div class ="col-md-offset-2 col-md-8">
                        <c:forEach var="hashTag" items="${blogPost.hashTags}">
                            <button type="button" class="btn btn-info btn-sm">#${hashTag.tag}</button>
                        </c:forEach>
                    </div>
                    <div row id="buttons">
                        <div class="col-md-4">
                            <form method="post" action="approvePost">
                                <button class="btn btn-default" name="approveButton" value="${blogPost.postID}">Approve</button>
                            </form>
                            <form method="post" action="deletePost">
                                <button class="btn btn-default" name="deleteButton" value="${blogPost.postID}">Decline</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
