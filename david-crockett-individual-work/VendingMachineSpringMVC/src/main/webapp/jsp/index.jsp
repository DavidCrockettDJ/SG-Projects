<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix ="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center;">Vending Machine</h1>
            <hr/>
            <div row>
                <div class="col-md-9">
                    <c:forEach var="currentItem" items="${items}">
                     <a href="itemSelection?itemId=${currentItem.itemId}" style="text-decoration: none; color: black;">
                    <div class="col-md-3" style="border-style: solid; margin: 25px; padding: 10px;">
                        <p style="text-align: left;"> <c:out value="${currentItem.itemId}"/></p>
                        <br/>
                        <p style="text-align: center;"> <c:out value="${currentItem.name}"/></p>
                        <br/>
                        <p style="text-align: center;"> $<c:out value="${currentItem.price}"/></p>
                        <br/>
                        <p style="text-align: center;"> Quantity:<c:out value="${currentItem.quantity}"/></p>
                    </div>
                     </a>
                    </c:forEach>
                </div>
                <div class="col-md-3">
                    <form class="form-horizontal" method="POST" role="form" action="addMoney" style="margin-top: 20px; text-align: center;">
                        <p class="col-md-offset-1" style="font-size: 30px;">Total $ in:</p>
                        <input type="text" class="form-control" value="${total}" style="text-align: center;" readonly/><br/>
                        <button type="submit" class="btn btn-default" style="text-align: center; width: 100px; height: 30px; background-color: green; color: white;" value="1.00" name="addDollar">Add Dollar</button>
                        <button type="submit" class="btn btn-default" style="text-align: center; width: 100px; height: 30px; background-color: green; color: white;" value="0.25" name="addQuarter">Add Quarter</button><br/>
                        <br/>
                        <button type="submit" class="btn btn-default" style="text-align: center; width: 100px; height: 30px; background-color: green; color: white;" value="0.10" name="addDime">Add Dime</button>
                        <button type="submit" class="btn btn-default" style="text-align: center; width: 100px; height: 30px; background-color: green; color: white;" value="0.05" name="addNickel">Add Nickel</button><br/>
                        <br/>
                        <hr/>
                    </form>
                    <form class="form-horizontal" method="GET" role="form" action="makePurchase" style="text-align: center;">
                        <p class="col-md-offset-1" style="font-size: 30px;">Messages</p>
                        <input type="text" class="form-control" value="${message}" style="text-align: center;" readonly/><br/>
                        <label for="item" style="text-align: center;">
                            Item:
                        </label>
                        <input type="text" class="form-control" value="${id}" style="text-align: center;" id="item" readonly/><br/>
                        <button type="submit" class="btn btn-default" style="text-align: center; width: 200px; background-color: blue; color: white;">Make Purchase</button><br/>
                        <br/>
                        <hr/>
                        <p style="font-size: 30px; text-align: center;">Change</p>
                        <input type="text" class="form-control" value="Q: ${q} D: ${d} N: ${n} P: ${p}" style="text-align: center; height: 50px;" readonly/><br/>
                        <br/>
                    </form>
                    <form class="form-horizontal" method="GET" role="form" action="getChange" style="text-align: center;">
                        <button type="submit" class="btn btn-default" style="width: 200px; height: 35px; background-color: #555555; color: white;">Get Change</button>
                    </form>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

