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

<c:if test="${showInfoAdded > 0}">
    <div class="container">
        <br>
        <div class="alert alert-success">
            Pomyślnie <strong>dodano nowe zlecenie!</strong>
        </div>
        <br>
    </div>
</c:if>
<div class="container">
    <h3>Dodawanie nowego zlecenia</h3>
    <br>
    <form class="form-horizontal" action="/null/orders/add" method="post">
        <div class="form-group">
            <label class="control-label col-sm-2" for="acceptanceDate">Data przyjęcia:</label>
            <div class="col-sm-10">
                <input type="date" class="form-control" name="acceptanceDate" id="acceptanceDate">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="scheduledStartDate">Planowana data rozp. naprawy:</label>
            <div class="col-sm-10">
                <input type="date" class="form-control" name="scheduledStartDate" id="scheduledStartDate">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="problemDescription">Opis problemu:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="problemDescription" id="problemDescription" placeholder="Podaj opis problemu..">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="vehicleId">ID pojazdu:</label>
            <div class="col-sm-10">
                <input type="number" min="1" step="1" class="form-control" name="vehicleId" id="vehicleId" placeholder="Podaj ID pojazdu..">
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
