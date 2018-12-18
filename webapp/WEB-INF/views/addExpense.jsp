<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://code.jquery.com/jquery-3.2.1.js" ></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<title>Insert title here</title>
</head>
<body>

<h1 class="text-center">Add expense</h1>
<div class="container">
    <form action="/Expenses/addExpense" method="post" >
        <div class="row">
            <div class="form-group col-md-2 col-md-offset-1">
                <label for="persons">Who</label>
                <select id="persons" name="personName" class="form-control"></select>
	        </div>
	        <div class="form-group col-md-2">
	            <label for="shops">Where</label>
	            <select id="shops" name="shopName" class="form-control"></select>
	        </div>
	        <div class="form-group col-md-2">
	            <label for="cities">City</label>
	            <select id="cities" name="cityName" class="form-control"></select>
	        </div>
	       <div class="form-group col-md-2">
                <label for="category">Category</label>
                <select id="category" name="categoryName" class="form-control"></select>
	        </div>
	        <div class="form-group col-md-2">
                <label for="personZwrotName">Person zwrot</label>
                <select id="personZwrotName" name="personZwrotName" class="form-control"></select>
            </div>
        </div>
        <div class="row">
	       <div class="form-group col-md-2 col-md-offset-4">
                <label for="cost">Cost</label>
                <input id="cost" name="koszt" type="text" class="form-control"></input>
            </div>
            <div class="form-group col-md-2 has-feedback">
                <label for="zwrot">Zwrot</label>
                <input id="zwrot" name="zwrot" type="text" class="form-control"></input>
            </div>
	    </div>
	    <div class="row">
	       <div class="form-group col-md-4 col-md-offset-4">
                <label for="data">Data</label>
                <input id="data" name="data" type="text" class="form-control"></input>
           </div>
	    </div>
	    <div class="row">
	       <div class="col-md-4 col-md-offset-4">
	           <button class="btn btn-primary form-control" type="submit">ADD</button>
	       </div>
	    </div>
    </form>
</div>

<c:set var="expensesDetails" value="${expenses}" scope="request" />
<jsp:include page="expensesDetailsTable.jsp" />

<script type="text/javascript">
var personsSelect = document.getElementById("persons")
<c:forEach items="${persons}" var="person">
	var option = document.createElement("option");
	option.text = "${person.name}";
	personsSelect.add(option);
</c:forEach>

var personZwrotNameSelect = document.getElementById("personZwrotName")
<c:forEach items="${persons}" var="person">
	var option = document.createElement("option");
	option.text = "${person.name}";
	personZwrotNameSelect.add(option);
</c:forEach>

var citiesSelect = document.getElementById("cities")
<c:forEach items="${cities}" var="city">
	var option = document.createElement("option");
	option.text = "${city.name}";
	citiesSelect.add(option);
</c:forEach>

var shopsSelect = document.getElementById("shops")
<c:forEach items="${shops}" var="shop">
	var option = document.createElement("option");
	option.text = "${shop.name}";
	shopsSelect.add(option);
</c:forEach>

var categoriesSelect = document.getElementById("category")
<c:forEach items="${expenseCategories}" var="category">
	var option = document.createElement("option");
	option.text = "${category.name}";
	categoriesSelect.add(option);
</c:forEach>

$( function() {
    $( "#data" ).datepicker(
    	{ dateFormat: 'yy-mm-dd' }
    );
  } );

$('#cost').change(function() {
    if($.isNumeric($('#cost').val())) {
    	$("#cost").css("background-color","#fff");
    } else {
    	$("#cost").css("background-color","red");
    }
});

$('#zwrot').change(function() {
	var patt = new RegExp(/[0-9]{1,3}/);
    if($.isNumeric($('#zwrot').val()) && patt.test($('#zwrot').val())) {
        $("#zwrot").css("background-color","#fff");
    } else {
        $("#zwrot").css("background-color","red");
    }
});

</script>

</body>
</html>