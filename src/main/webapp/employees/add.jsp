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

<c:if test="${showInfoAdded > 0}">
    <div class="container">
        <br>
        <div class="alert alert-success">
            Pomyślnie <strong>dodano nowego pracownika!</strong>
        </div>
        <br>
    </div>
</c:if>
<div class="container">
    <h3>Dodawanie nowego pracownika</h3>
    <br>
    <form class="form-horizontal" action="/null/employees/add" method="post">
        <div class="form-group">
            <label class="control-label col-sm-2" for="firstName">Imię:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="firstName" id="firstName" placeholder="Podaj imię..">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="lastName">Nazwisko:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Podaj nazwisko..">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="address">Dane adresowe:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="address" id="address" placeholder="Podaj adres..">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="phone">Telefon:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="phone" id="phone" placeholder="Podaj numer telefonu..">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="note">Natatka:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="note" id="note" placeholder="Podaj notatkę..">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="manHourCost">Koszt roboczogodziny:</label>
            <div class="col-sm-10">
                <input type="number" min="0" step="0.01" class="form-control" name="manHourCost" id="manHourCost" placeholder="Podaj koszt roboczogodziny..">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-4">
                <button type="submit" class="btn btn-info center-block">Zapisz</button>
            </div>
        </div>
    </form>
    <br>
</div>

<%@include file="../footer.jsp" %>
</body>
</html>
