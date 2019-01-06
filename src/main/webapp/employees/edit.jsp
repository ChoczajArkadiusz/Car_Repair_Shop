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

<h2 class="text-center"> Pracownicy </h2>

<c:if test="${showInfoEdited > 0}">
    <div class="container">
        <br>
        <div class="alert alert-success">
            Pomyślnie <strong>zaktualizowano</strong> dane pracownika!
        </div>
        <br>
    </div>
</c:if>

<c:if test="${showInfoDeleted > 0}">
    <div class="container">
        <br>
        <div class="alert alert-success">
            Pomyślnie <strong>usunięto</strong> dane pracownika!
        </div>
        <br>
    </div>
</c:if>

<div class="container">
    <h3>Aktualna postać wpisu:</h3>
    <c:if test="${not empty employee && employee.id != 0}">
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th> ID</th>
                <th> Imię</th>
                <th> Nazwisko</th>
                <th> Dane adresowe</th>
                <th> Numer telefonu</th>
                <th> Notatka</th>
                <th> Koszt roboczogodziny</th>
            </tr>
            </thead>
            <tbody id="employeeTab">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.firstName}</td>
                <td>${employee.lastName}</td>
                <td>${employee.address}</td>
                <td>${employee.phone}</td>
                <td>${employee.note}</td>
                <td>${employee.manHourCost}</td>
            </tr>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty employee || employee.id == 0}">
        <h4>Nie znaleziono wpisu w bazie.</h4>
    </c:if>
</div>


<c:if test="${not empty employee && employee.id != 0}">
    <div class="container">
        <h3>Edycja danych:</h3>
        <br>
        <form class="form-horizontal" action="/null/employees/edit" method="post">
            <div class="form-group">
                <input type="hidden" name="employeeId" id="employeeId" value="${employee.id}">
                <label class="control-label col-sm-2" for="firstName">Imię:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="firstName" id="firstName"
                           value="${employee.firstName}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="lastName">Nazwisko:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="lastName" id="lastName" value="${employee.lastName}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="address">Dane adresowe:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="address" id="address" placeholder="Podaj adres.." value="${employee.address}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="phone">Telefon:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="phone" id="phone" placeholder="Podaj numer telefonu.." value="${employee.phone}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="note">Natatka:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="note" id="note" placeholder="Podaj notatkę.." value="${employee.note}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="manHourCost">Koszt roboczogodziny:</label>
                <div class="col-sm-10">
                    <input type="number" min="0" step="0.01" class="form-control" name="manHourCost" id="manHourCost" placeholder="Podaj koszt roboczogodziny.." value="${employee.manHourCost}">
                </div>
            </div>
            <br>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <button type="submit" class="btn btn-info center-block">Zapisz</button>
                </div>
            </div>
        </form>
        <hr>
        <form class="form-horizontal" action="/null/employees/delete" method="post">
            <div class="form-group">
                <input type="hidden" name="employeeIdDelete" id="employeeIdDelete" value="${employee.id}">
                <div class="col-sm-offset-4 col-sm-4">
                    <button type="submit" class="btn btn-warning center-block">Usuń wpis z bazy danych</button>
                </div>
            </div>
        </form>
        <br>
    </div>
</c:if>

<%@include file="../footer.jsp" %>
</body>
</html>
