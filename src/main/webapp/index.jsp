<%--
  Created by IntelliJ IDEA.
  User: arek
  Date: 05.01.19
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Warsztat samochodowy</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<h1 class="text-center">Warsztat samochodowy</h1>
<h3 class="text-center">Aktualnie prowadzone naprawy</h3>

<div class="container">
    <h3>Lista wpisów w bazie danych:</h3>
    <c:if test="${not empty ordersInfos}">
        <p>Wyszukaj w tabeli:</p>
        <input class="form-control" id="searchPhrase" type="text" placeholder="szukana fraza..">
        <br>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th> Pracownik</th>
                <th> Data rozp. naprawy</th>
                <th> Pojazd</th>
                <th> Opis problemu</th>
                <th> Roboczogodziny</th>
                <th> Szczegóły</th>
            </tr>
            </thead>
            <tbody id="ordersTab">
            <c:forEach items="${ordersInfos}" var="orderInfo">
                <tr>
                    <td>${orderInfo.employee.firstName} ${orderInfo.employee.lastName}</td>
                    <td>${orderInfo.order.startDate}</td>
                    <td>${orderInfo.vehicle.manufacturer} ${orderInfo.vehicle.model}</td>
                    <td>${orderInfo.order.problemDescription}</td>
                    <td>${orderInfo.order.manHours}</td>
                    <td>
                        <a href="/null/orders/edit?orderId=${orderInfo.order.id}" class="btn btn-info btn-block btn-sm"
                           role="button">Szczegóły zlecenia</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty ordersInfos}">
        <h4>brak wpisów</h4>
        <br>
        <br>
        <a href="/null/orders_info"> odśwież </a>

    </c:if>
</div>


<script>
    $(document).ready(function () {
        $("#searchPhrase").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#ordersTab tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>




</body>
</html>
