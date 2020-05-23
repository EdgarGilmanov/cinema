<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="x-UTF-16LE-BOM">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Cinema "Galaxy"</title>
</head>
<body onload="showModel()">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
<script>
    function reloadPage() {
        location.reload();
    }
    setInterval("reloadPage()", 100000);
</script>
<script>
    function showModel() {
        var rad=document.getElementsByName('place');
        for (var i=0; i<rad.length; i++) {
            var params = rad[i].id.split('&');
            var row = params[0];
            var place = params[1];
            isPlaceTaken(row, place, rad[i].id);
        }
    }
</script>
<script>
    function isPlaceTaken(row, place, id) {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8088/cinema/getModel',
            data: 'row=' + row + '&place=' + place,
            dataType: 'text'
        }).done(function(data) {
            if (data.trim() == 'true') {
                document.getElementById(id).disabled = true;
            }
        }).fail(function(){
            alert("Dont work");
        });
    }
</script>

<script>
    function toPay() {
        var rsl;
        var rad=document.getElementsByName('place');
        for (var i=0;i<rad.length; i++) {
            if (rad[i].checked) {
                rsl = rad[i].id;
            }
        }
        window.location.assign("payment.html?" + rsl);
    }
</script>
<div class="container">
    <div class="row pt-3">
        <h4>
            Booking a place for a session
        </h4>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th style="width: 120px;">Row / Place</th>
                <th>1</th>
                <th>2</th>
                <th>3</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>1</th>
                <td><input type="radio" id="1&1" name="place" value="11"> Row 1, Place 1</td>
                <td><input type="radio" id="1&2" name="place" value="11"> Row 1, Place 2</td>
                <td><input type="radio" id="1&3" name="place" value="11"> Row 1, Place 3</td>
            </tr>
            <tr>
                <th>2</th>
                <td><input type="radio" id="2&1" name="place" value="11"> Row 2, Place 1</td>
                <td><input type="radio" id="2&2" name="place" value="11"> Row 2, Place 2</td>
                <td><input type="radio" id="2&3" name="place" value="11"> Row 2, Place 3</td>
            </tr>
            <tr>
                <th>3</th>
                <td><input type="radio" id="3&1" name="place" value="11"> Row 3, Place 1</td>
                <td><input type="radio" id="3&2" name="place" value="11"> Row 3, Place 2</td>
                <td><input type="radio" id="3&3" name="place" value="11"> Row 3, Place 3</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="row float-right">
        <button type="button" class="btn btn-success" onclick="toPay()">Pay</button>
    </div>
</div>
</body>
</html>