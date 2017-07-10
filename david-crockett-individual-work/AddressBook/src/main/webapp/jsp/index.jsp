<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Address Book</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center;">Address Book</h1>
            <hr/>
            <div class='row'>
            <div class="col-md-8">
            <table id="addressTable" class="table table-hover">
                <tr>
                    <th width="20%">Name</th>
                    <th width="30%">Street Address</th>
                    <th width="15%">City</th>
                    <th width="15%">Zipcode</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="currentAddress" items="${Addresses}">
                    <tr>
                        <td><c:out value="${currentAddress.firstName}"/> <c:out value="${currentAddress.lastName}"/></td>
                        <td><c:out value="${currentAddress.streetAddress}"/></td>
                        <td><c:out value="${currentAddress.city}"/></td>
                        <td><c:out value="${currentAddress.zipcode}"/></td>
                        <td>Edit</td>   
                    <form action="deleteAddress" method="GET" role="form">
                        <td><button type="submit" value="${currentAddress.id}" class="btn btn-default" style="background-color: red; color: white;" name="deleteButton">Delete</button></td>
                    </form>
                    </tr>
                </c:forEach>
            </table>
            </div>
                <div class="col-md-4">
                    <form method="POST" role="role" action="addAddress">
                        <p style="text-align: center; font-size: 20px;">First Name:</p>
                        <input type='text' class='form-control' style='text-align: center;' name="firstName"/>
                        <p style="text-align: center; font-size: 20px;">Last Name:</p>
                        <input type='text' class='form-control' style='text-align: center;' name="lastName"/>
                        <p style="text-align: center; font-size: 20px;">Street Address:</p>
                        <input type='text' class='form-control' style='text-align: center;' name="streetAddress"/>
                        <p style="text-align: center; font-size: 20px;">City:</p>
                        <input type='text' class='form-control' style='text-align: center;' name="city"/>
                        <p style="text-align: center; font-size: 20px;">Zipcode:</p>
                        <input type='text' class='form-control' style='text-align: center;' name="zipcode"/>
                        <br/>
                        <br/>
                        <div style="text-align: center;">
                        <input type="submit" class="btn btn-default" style="text-align: center; background-color: black; color: white;" value="Add Address!"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

