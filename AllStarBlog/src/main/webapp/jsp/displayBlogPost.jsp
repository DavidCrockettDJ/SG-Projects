<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <link href="${pageContext.request.contextPath}/css/AllStarBlog.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/webApp.css" rel="stylesheet">

        <script>
            document.getElementById('accountChoice').onchange = function () {
                window.location.href = this.children[this.selectedIndex].getAttribute('href');
            }
        </script>
        <title>Bethesda</title>

    <div id="background"></div>
    <!--    Navbar-->
    <nav class="navbar navbar-default">
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
                    <li id="loginButton"><a href="${pageContext.request.contextPath}/loginPage">Login</a></li>
                        <sec:authorize access="hasAnyRole('ROLE_ADMIN, ROLE_USER')">
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
                            </li>
                        </sec:authorize>
                    </ul>
                    <form method="get" action="categorizeBlogPosts" class="form-inline my-2 my-lg-0 navbar-right">
                        <input class="form-control mr-sm-2" name="hashTagSearch" type="text" placeholder="Search By #Hashtag">
                    </form>
                </div>
            </div>
        </nav>
    </head>
    <body>
        <div class="container">


            <div id="postDisplayContainer" class="col-md-offset-2 col-md-8">
                <div id="postTitle" class="col-md-12">
                    ${post.title}
                </div>
                <div id="postDescription" class="col-md-12">
                    ${post.description}
                </div>
                <div id="postFooter" class="col-md-12">
                    <div id="postTags" class="col-md-9">
                        <c:forEach var="tag" items="${post.hashTags}">#${tag.tag}
                        </c:forEach>
                    </div>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN, ROLE_USER')">
                        <div id="editDelete" class="col-md-3">
                            <form action="updatePost" method="GET" role="form" id="updatePost">
                                <div class="col-md-6">
                                    <button type="submit" name="editButton" value="${post.postID}" class="btn btn-default">Edit</button>
                                </div>
                            </form>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <form action="static/deletePost" method="POST" role="form" id="deletePost">
                                <div class="col-md-6">
                                    <button type="submit" name="deleteButton" value="${post.postID}" class="btn btn-default">Delete</button>
                                </div>
                            </form>
                        </div>
                    </sec:authorize>
                </div>


            </div>
        </div>
        <div class="navbar-fixed-bottom navbar navbar-default">
            <div class="col-md-offset-1 col-md-10" id="footer">

                <form method="post" action="static/displayStaticPage" name="staticPage"id="staticPageForm">
                    <ul class="nav navbar-nav">
                        <c:forEach var="post" items="${pageList}">
                            <c:forEach var="hashTag" items="${post.hashTags}">

                                <li>&nbsp <button type="submit" name="staticPage" id="staticButton" value="${hashTag.hashTagID}">${hashTag.tag}  </button>&nbsp |</li> 

                            </c:forEach>
                        </c:forEach>
                    </ul>
                </form>  
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/AllStarBlog.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>

</html>

