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

<c:if test="${showInfoAdded > 0}">
    <div class="container">
        <br>
        <div class="alert alert-success">
            Pomyślnie <strong>dodano nowy pojazd!</strong>
        </div>
        <br>
    </div>
</c:if>
<div class="container">
    <h3>Dodawanie nowego pojazdu</h3>
    <br>
    <form class="form-horizontal" action="/null/vehicles/add" method="post">
        <div class="form-group">
            <label class="control-label col-sm-2" for="manufacturer">Marka:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="manufacturer" id="manufacturer" placeholder="Podaj markę..">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="model">Model:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="model" id="model" placeholder="Podaj model..">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="yearOfProduction">Rok produkcji:</label>
            <div class="col-sm-10">
                <input type="number" min="1900" step="1" class="form-control" name="yearOfProduction" id="yearOfProduction" placeholder="Podaj rok produkcji..">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="plateNumber">Numer rejestracyjny:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="plateNumber" id="plateNumber" placeholder="Podaj numer rejestracyjny..">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="nextReviewDate">Data nast. przeglądu:</label>
            <div class="col-sm-10">
                <input type="date" class="form-control" name="nextReviewDate" id="nextReviewDate">
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
