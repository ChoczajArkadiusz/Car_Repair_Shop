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

<h2 class="text-center"> Przypisywanie klienta do pojazdu ${vehicle.manufacturer} ${vehicle.model} - ${vehicle.plateNumber} </h2>

<div class="container">
    <h3>Lista wpisów w bazie danych:</h3>
    <c:if test="${not empty customers}">
        <p>Wyszukaj w tabeli:</p>
        <input class="form-control" id="searchPhrase" type="text" placeholder="szukana fraza..">
        <br>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th> ID</th>
                <th> Imię</th>
                <th> Nazwisko</th>
                <th> Przypisanie</th>
            </tr>
            </thead>
            <tbody id="customersTab">
            <c:forEach items="${customers}" var="customer">
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.firstName}</td>
                    <td>${customer.lastName}</td>
                    <td>
                        <form class="form" action="/null/customers_vehicles/assign_to_vehicle" method="post">
                            <div class="form-group">
                                <input type="hidden" name="vehicleId" id="vehicleId" value="${vehicle.id}">
                                <input type="hidden" name="customerId" id="customerId" value="${customer.id}">
                                <button type="submit" class="btn btn-warning center-block">Przypisz</button>
                            </div>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty customers}">
        <h4>brak wpisów</h4>
    </c:if>
</div>

<script>
    $(document).ready(function () {
        $("#searchPhrase").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#vehiclesTab tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>

<%@include file="../footer.jsp" %>
</body>
</html>
