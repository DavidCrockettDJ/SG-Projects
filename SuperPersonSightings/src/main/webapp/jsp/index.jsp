<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SuperTracker</title>
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
                    <li class='active'><a href='${pageContext.request.contextPath}'><span>Home</span></a></li>
                    <li><a href='organizations'><span>Organizations</span></a></li>
                    <li><a href='superPeople'><span>SuperPeople</span></a></li>
                    <li><a href='locations'><span>Locations</span></a></li>
                    <li class='last'><a href='sightings'><span>Sightings</span></a></li>
                </ul>
            </div>
            <br/>
            <br/>
            <table class="contents">
            <thead>
            <tr>
                <th>Sighting</th>
                <th>Date</th>
            </tr>
            </thead>
            <c:forEach var="currentSight" items="${sightList}">
            <tr>
                <td><a href="viewSighting?Sightingid=${currentSight.sightingid}" style="text-decoration: none;"><c:out value="${currentSight.location.name}"/></a></td>
                <td><c:out value="${currentSight.date}"/></td>
            </tr>
            </c:forEach>
            </table>
            
        </div>
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

