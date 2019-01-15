<div class="container">
    <h3>Dodawanie nowego klienta</h3>
    <br>
    <form class="form-horizontal" action="/null/customers/add" method="post">
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
            <label class="control-label col-sm-2" for="birthDate">Data urodzenia:</label>
            <div class="col-sm-10">
                <input type="date" class="form-control" name="birthDate" id="birthDate">
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
