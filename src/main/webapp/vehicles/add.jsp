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

<%@include file="form_add.jsp" %>

<%@include file="../footer.jsp" %>
</body>
</html>
