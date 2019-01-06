<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/null/orders_info">Warsztat samochodowy</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Klienci
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/null/customers/show_all">Wyświetl wszystkich</a></li>
                    <li><a href="/null/customers/add">Dodaj nowego</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Pracownicy
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/null/employees/show_all">Wyświetl wszystkich</a></li>
                    <li><a href="/null/employees/add">Dodaj nowego</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Pojazdy
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/null/vehicles/show_all">Wyświetl wszystkie</a></li>
                    <li><a href="/null/vehicles/add">Dodaj nowy</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Zlecenia
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/null/orders/show_all">Wyświetl wszystkie</a></li>
                    <li><a href="/null/orders/add">Dodaj nowe</a></li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Login</a></li>
        </ul>
    </div>
</nav>