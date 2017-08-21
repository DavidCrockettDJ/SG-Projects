<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Locations</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/menu.css" rel="stylesheet">
    </head>
    <header>
        <h1 style="text-align: center;">Super Tracker 3.0</h1>
    </header>
    <body>
        <div class="container">
            <div id='cssmenu'>
                <ul>
                    <li class='active'><a href='home'><span>Home</span></a></li>
                    <li><a href='organizations'><span>Organizations</span></a></li>
                    <li><a href='superPeople'><span>SuperPeople</span></a></li>
                    <li><a href="${pageContext.request.contextPath}"><span>Locations</span></a></li>
                    <li class='last'><a href='sightings'><span>Sightings</span></a></li>
                </ul>
            </div>
            <br/>
            <br/>
            <table class="contents">
            <thead>
            <tr>
                <th>Location</th>
                <th>Description</th>
            </tr>
            </thead>
            <c:forEach var="currentLoc" items="${locList}">
            <tr>
                <td><a href="viewLocation?Locationid=${currentLoc.locationid}" style="text-decoration: none;"><c:out value="${currentLoc.name}"/></a></td>
                <td><c:out value="${currentLoc.description}"/></td>
            </tr>
            </c:forEach>
            </table>
            <br/>
            <br/>
            <div style='text-align: center;'>
                <a href='addLocation'><button class='btn btn-default' style='background-color: black; color: #0f71ba'>Add Location</button></a>
            </div>
        </div>
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
