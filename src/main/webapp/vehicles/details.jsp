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

<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">Szczegółowe informacje o pojeździe</div>
        <div class="panel-body">
            <h3>Aktualna postać wpisu:</h3>
            <c:if test="${not empty vehicle && vehicle.id != 0}">
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
                <tbody id="customerTab">
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
                </tbody>
            </table>
            </c:if>
            <c:if test="${empty vehicle}">
                <h4>brak wpisów</h4>
            </c:if>
            <br>
        </div>
    </div>

    <div class="panel panel-info">
        <div class="panel-heading">Klienci przypisani do pojazdu:</div>
        <div class="panel-body">
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
                    <th> Data urodzenia</th>
                    <th> Edycja</th>
                    </tr>
                </thead>
                <tbody id="customersTab">
                <c:forEach items="${customers}" var="customer">
                    <tr>
                        <td>${customer.id}</td>
                        <td>${customer.firstName}</td>
                        <td>${customer.lastName}</td>
                        <td>${customer.birthDate}</td>
                        <td>
                            <a href="/null/customers/edit?customerId=${customer.id}" class="btn btn-info btn-block btn-sm"
                               role="button">Edytuj</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                </table>
            </c:if>
            <c:if test="${empty customers}">
                <h4>brak wpisów</h4>
            </c:if>

            <hr>


            <form class="form" action="/null/customers_vehicles/assign_to_vehicle" method="get">
                <div class="form-group">
                    <button type="submit" class="btn btn-info center-block">Przypisz nowego klienta z bazy danych</button>
                </div>
            </form>


            <button class="btn btn-info center-block" type="button" data-toggle="collapse" data-target="#newCustomer">
                Utwórz nowego klienta
            </button>


        </div>
    </div>

    <div id="newCustomer" class="collapse col-sm-offset--1">
        <%@include file="../customers/form_add.jsp" %>
    </div>

    <br>
    <br>

    <%@include file="../footer.jsp" %>
</body>
</html>
