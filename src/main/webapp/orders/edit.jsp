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

<c:if test="${showInfoEdited > 0}">
    <div class="container">
        <br>
        <div class="alert alert-success">
            Pomyślnie <strong>zaktualizowano</strong> dane zlecenia!
        </div>
        <br>
    </div>
</c:if>

<c:if test="${showInfoDeleted > 0}">
    <div class="container">
        <br>
        <div class="alert alert-success">
            Pomyślnie <strong>usunięto</strong> dane zlecenia!
        </div>
        <br>
    </div>
</c:if>

<div class="container">
    <h3>Aktualna postać wpisu:</h3>
    <c:if test="${not empty order && order.id != 0}">
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
            </tr>
            </thead>
            <tbody id="orderTab">
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
            </tr>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty order || order.id == 0}">
        <h4>Nie znaleziono wpisu w bazie.</h4>
    </c:if>
</div>


<c:if test="${not empty order && order.id != 0}">
    <div class="container">
        <h3>Edycja danych:</h3>
        <br>
        <form class="form-horizontal" action="/null/orders/edit" method="post">
            <input type="hidden" name="orderId" id="orderId" value="${order.id}">
            <div class="form-group">
                <label class="control-label col-sm-2" for="acceptanceDate">Data przyjęcia:</label>
                <div class="col-sm-10">
                    <input type="date" class="form-control" name="acceptanceDate" id="acceptanceDate" value="${order.acceptanceDate}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="scheduledStartDate">Planowana data rozp. naprawy:</label>
                <div class="col-sm-10">
                    <input type="date" class="form-control" name="scheduledStartDate" id="scheduledStartDate" value="${order.scheduledStartDate}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="startDate">Data rozp. naprawy:</label>
                <div class="col-sm-10">
                    <input type="date" class="form-control" name="startDate" id="startDate" value="${order.startDate}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="employeeId">ID pracownika:</label>
                <div class="col-sm-10">
                    <input type="number" min="1" step="1" class="form-control" name="employeeId" id="employeeId" placeholder="Podaj ID pracownika.." value="${order.employeeId}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="problemDescription">Opis problemu:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="problemDescription" id="problemDescription" placeholder="Podaj opis problemu.." value="${order.problemDescription}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="repairDescription">Opis naprawy:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="repairDescription" id="repairDescription" placeholder="Podaj opis naprawy.." value="${order.repairDescription}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="status">Status:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="status" id="status" placeholder="Podaj status.." value="${order.status}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="vehicleId">ID pojazdu:</label>
                <div class="col-sm-10">
                    <input type="number" min="1" step="1" class="form-control" name="vehicleId" id="vehicleId" placeholder="Podaj ID pojazdu.." value="${order.vehicleId}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="manHours">Roboczogodziny:</label>
                <div class="col-sm-10">
                    <input type="number" min="0.0" step="0.25" class="form-control" name="manHours" id="manHours" placeholder="Podaj ilość roboczogodzin.." value="${order.manHours}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="manHourCost">Koszt roboczogodziny:</label>
                <div class="col-sm-10">
                    <input type="number" min="0.0" step="0.01" class="form-control" name="manHourCost" id="manHourCost" placeholder="Podaj ID pojazdu.." value="${order.manHourCost}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="partsCost">Koszt części:</label>
                <div class="col-sm-10">
                    <input type="number" min="0.0" step="0.01" class="form-control" name="partsCost" id="partsCost" placeholder="Podaj koszt części.." value="${order.partsCost}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="costForCustomer">Koszt dla klienta:</label>
                <div class="col-sm-10">
                    <input type="number" min="0.0" step="0.01" class="form-control" name="costForCustomer" id="costForCustomer" placeholder="Podaj koszt dla klienta.." value="${order.costForCustomer}">
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
        <form class="form-horizontal" action="/null/orders/delete" method="post">
            <div class="form-group">
                <input type="hidden" name="orderIdDelete" id="orderIdDelete" value="${order.id}">
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
