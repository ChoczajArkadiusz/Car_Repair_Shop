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

<h2 class="text-center"> Pojazdy </h2>

<div class="container">
    <h3>Lista wpisów w bazie danych:</h3>
    <c:if test="${not empty vehicles}">
        <p>Wyszukaj w tabeli:</p>
        <input class="form-control" id="searchPhrase" type="text" placeholder="szukana fraza..">
        <br>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th> ID</th>
                <th> Marka</th>
                <th> Model</th>
                <th> Rok produkcji</th>
                <th> Numer rejestracyjny</th>
                <th> Data nast. przeglądu</th>
                <th> Edycja</th>
            </tr>
            </thead>
            <tbody id="vehiclesTab">
            <c:forEach items="${vehicles}" var="vehicle">
                <tr>
                    <td>${vehicle.id}</td>
                    <td>${vehicle.manufacturer}</td>
                    <td>${vehicle.model}</td>
                    <td>${vehicle.yearOfProduction}</td>
                    <td>${vehicle.plateNumber}</td>
                    <td>${vehicle.nextReviewDate}</td>
                    <td>
                        <a href="/null/vehicles/edit?vehicleId=${vehicle.id}" class="btn btn-info btn-block btn-sm"
                           role="button">Edytuj</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty vehicles}">
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
