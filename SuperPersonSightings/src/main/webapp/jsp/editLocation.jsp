<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>AddLocation</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <script>
            $(function () {
                $("#datepicker").datepicker();
            });
        </script>
        <style type="text/css">
            .form-style-9{
                max-width: 450px;
                background: #FAFAFA;
                padding: 30px;
                margin: 50px auto;
                box-shadow: 1px 1px 25px rgba(0, 0, 0, 0.35);
                border-radius: 10px;
                border: 6px solid #305A72;
            }
            .form-style-9 ul{
                padding:0;
                margin:0;
                list-style:none;
            }
            .form-style-9 ul li{
                display: block;
                margin-bottom: 10px;
                min-height: 35px;
            }
            .form-style-9 ul li  .field-style{
                box-sizing: border-box; 
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box; 
                padding: 8px;
                outline: none;
                border: 1px solid #B0CFE0;
                -webkit-transition: all 0.30s ease-in-out;
                -moz-transition: all 0.30s ease-in-out;
                -ms-transition: all 0.30s ease-in-out;
                -o-transition: all 0.30s ease-in-out;

            }.form-style-9 ul li  .field-style:focus{
                box-shadow: 0 0 5px #B0CFE0;
                border:1px solid #B0CFE0;
            }
            .form-style-9 ul li .field-split{
                width: 49%;
            }
            .form-style-9 ul li .field-full{
                width: 100%;
            }
            .form-style-9 ul li input.align-left{
                float:left;
            }
            .form-style-9 ul li input.align-right{
                float:right;
            }
            .form-style-9 ul li textarea{
                width: 100%;
                height: 100px;
            }
            .form-style-9 ul li input[type="button"], 
            .form-style-9 ul li input[type="submit"] {
                -moz-box-shadow: inset 0px 1px 0px 0px #3985B1;
                -webkit-box-shadow: inset 0px 1px 0px 0px #3985B1;
                box-shadow: inset 0px 1px 0px 0px #3985B1;
                background-color: #216288;
                border: 1px solid #17445E;
                display: inline-block;
                cursor: pointer;
                color: #FFFFFF;
                padding: 8px 18px;
                text-decoration: none;
                font: 12px Arial, Helvetica, sans-serif;
            }
            .form-style-9 ul li input[type="button"]:hover, 
            .form-style-9 ul li input[type="submit"]:hover {
                background: linear-gradient(to bottom, #2D77A2 5%, #337DA8 100%);
                background-color: #28739E;
            }
            .dropbtn {
                background-color: #216288;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }
            .dropdown:hover .dropbtn {
                background-color: #216288;
            }
        </style>
</head>
<body>
    <h1 style="text-align: center; color: activecaption;">Edit Location!</h1>

    <form class="form-style-9" action='editLocation' method='post'>
        <ul>
            <li>
                <input type="text" name="locationName" class="field-style field-split align-left" value="${location.name}" placeholder="Name" required/>
                <input type="text" name="locationDate" class="field-style field-split align-right" value="${location.date}" id="datepicker" placeholder="Date" readonly/>
            </li>
            <li>
                <input type="text" name="locationDescription" class="field-style field-full align-none" value="${location.description}" placeholder="Description" required/>
            </li>
            <li>
                <input type="text" name="locationAddress" class="field-style field-full align-none" value="${location.address}" placeholder="Address" required/>
            </li>
            <li>
                <input type="number" step="any" name="locationLongitude" class="field-style field-split align-left" value="${location.longitude}" placeholder="00.00" required/>
                <input type="number" step="any" name="locationLatitude" class="field-style field-split align-right" value="${location.latitude}" placeholder="00.00" required/>
            </li>
            <li>
                <div style='text-align: center;'>
                    <button type="submit" class="dropbtn" value="${location.locationid}" name="editButton">Submit</button>
                </div>
            </li>
        </ul>
    </form>
   
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
