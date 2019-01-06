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
<%@include file="../header.jsp" %>

<h2 class="text-center"> Zlecenia </h2>

<div class="container">
    <h3>Lista wpisów w bazie danych:</h3>
    <c:if test="${not empty orders}">
        <p>Wyszukaj w tabeli:</p>
        <input class="form-control" id="searchPhrase" type="text" placeholder="szukana fraza..">
        <br>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th> ID</th>
                <th> Data przyjęcia</th>
                <th> Planowana data rozp. naprawy</th>
                <th> Data rozp. naprawy</th>
                <th> ID pracownika</th>
                <th> Opis problemu</th>
                <th> Opis naprawy</th>
                <th> Status</th>
                <th> ID pojazdu</th>
                <th> Roboczogodziny</th>
                <th> Koszt roboczogodziny</th>
                <th> Koszt części</th>
                <th> Koszt dla klienta</th>
                <th> Edycja</th>
            </tr>
            </thead>
            <tbody id="ordersTab">
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.acceptanceDate}</td>
                    <td>${order.scheduledStartDate}</td>
                    <td>${order.startDate}</td>
                    <td>${order.employeeId}</td>
                    <td>${order.problemDescription}</td>
                    <td>${order.repairDescription}</td>
                    <td>${order.status}</td>
                    <td>${order.vehicleId}</td>
                    <td>${order.manHours}</td>
                    <td>${order.manHourCost}</td>
                    <td>${order.partsCost}</td>
                    <td>${order.costForCustomer}</td>
                    <td>
                        <a href="/null/orders/edit?orderId=${order.id}" class="btn btn-info btn-block btn-sm"
                           role="button">Edytuj</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty orders}">
        <h4>brak wpisów</h4>
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

<%@include file="../footer.jsp" %>
</body>
</html>
