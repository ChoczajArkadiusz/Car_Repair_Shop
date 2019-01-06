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

<h2 class="text-center"> Klienci </h2>

<c:if test="${showInfoEdited > 0}">
    <div class="container">
        <br>
        <div class="alert alert-success">
            Pomyślnie <strong>zaktualizowano</strong> dane klienta!
        </div>
        <br>
    </div>
</c:if>

<c:if test="${showInfoDeleted > 0}">
    <div class="container">
        <br>
        <div class="alert alert-success">
            Pomyślnie <strong>usunięto</strong> dane klienta!
        </div>
        <br>
    </div>
</c:if>

<div class="container">
    <h3>Aktualna postać wpisu:</h3>
    <c:if test="${not empty customer && customer.id != 0}">
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th> ID</th>
                <th> Imię</th>
                <th> Nazwisko</th>
                <th> Data urodzenia</th>
            </tr>
            </thead>
            <tbody id="customerTab">
            <tr>
                <td>${customer.id}</td>
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td>${customer.birthDate}</td>
            </tr>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty customer || customer.id == 0}">
        <h4>Nie znaleziono wpisu w bazie.</h4>
    </c:if>
</div>


<c:if test="${not empty customer && customer.id != 0}">
    <div class="container">
        <h3>Edycja danych:</h3>
        <br>
        <form class="form-horizontal" action="/null/customers/edit" method="post">
            <div class="form-group">
                <input type="hidden" name="customerId" id="customerId" value="${customer.id}">
                <label class="control-label col-sm-2" for="firstName">Imię:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="firstName" id="firstName"
                           value="${customer.firstName}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="lastName">Nazwisko:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="lastName" id="lastName" value="${customer.lastName}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="birthDate">Data urodzenia:</label>
                <div class="col-sm-10">
                    <input type="date" class="form-control" name="birthDate" id="birthDate" ,
                           value="${customer.birthDate}">
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
        <form class="form-horizontal" action="/null/customers/delete" method="post">
            <div class="form-group">
                <input type="hidden" name="customerIdDelete" id="customerIdDelete" value="${customer.id}">
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
