<%-- 
    Document   : addPost
    Created on : Jul 21, 2017, 9:56:01 AM
    Author     : Becca
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head> 
        <title>Bethesda</title>

        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/tinymce/jquery.tinymce.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/tinymce/tinymce.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/AllStarBlog.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap-tagsinput.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap-tagsinput-angular.js"></script>
        <link href="${pageContext.request.contextPath}/css/AllStarBlog.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/bootstrap-tagsinput.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/webApp.css" rel="stylesheet">
        <script>
            tinymce.activeEditor.uploadImages(function (success) {
                document.forms[0].submit();
            });
        </script>

            <script>
                $(document).ready(function () {
                $('#blogCheck').hide();
                $('#pending').hide();
            });
            </script>
            
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
                <form class="form-vertical" 
                      role="form" method="POST" 
                      action="addBlogPost"
                      id="blogPost">
                    <div class="form-group">
                        <div class="col-md-9">
                            <label for="hashTags">HashTags:</label>
                            <br/>
                            <select data-role="tagsinput" multiple="multiple" name="tags" id="hashTags">
                                <option value="Bethesda">Bethesda</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-9">
                        <label style="text-align: left;" for="picture">Picture URL:</label>
                            <input type="text" class="form-control" id="picture" name="picture" value="http://cdn4.gamepur.com/images/feature/bethesda-logo.jpg" required>
                            <br/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-9">
                        <label style="text-align: left;" for="picture">Blog Title:</label>
                            <input type="text" class="form-control" name="title" required>
                            <br/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-9">
                            <textarea class = "tinymce" name="texteditor" form="blogPost"></textarea>
                            <br/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-9">
                            <button type="submit" name="addBlogPost" value="Add Post" class="btn btn-default">Add Post</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
