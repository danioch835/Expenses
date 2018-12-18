<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>

<form method="post" enctype="multipart/form-data" action="/Expenses/loadDataFromExcel">
    <input type="file" name="file" accept=".xls,.xlsx" /> 
    <input type="submit" value="Upload file" />
</form>

<input type="button" id="fileExpensesValidation" value="Validate file" />

<c:if test="${not empty correctExpensesLoaded}">
  <c:set var="loadedExpensesInfo" value="Wczytano poprawnie: ${correctExpensesLoaded}" />
</c:if>

<p id="correctExpensesInfo">${loadedExpensesInfo}</p>
<p id="incorrectExpensesInfo"></p>

<c:set var="expensesDetails" value="${expenses}" scope="request" />
<jsp:include page="expensesDetailsTable.jsp" />

<c:if test="${not empty uploadedFileName}">
<form method="POST" action="/Expenses/saveExpensesFromExcelFile">
  <input type="hidden" name="fileName" value="${uploadedFileName}">
  <input type="submit" value="Save expenses">
</form>
</c:if>

<script type="text/javascript">
 document.getElementById("fileExpensesValidation").onclick = function() {
	 var formData = new FormData();
	 var inputFileElement = document.getElementsByName("file")[0];
	 var file = inputFileElement.files[0];
	 if(file != undefined) {
		 formData.set("file", file);
		 var request = new XMLHttpRequest();
		 request.open("POST", "/Expenses/checkExpensesInExcelFile", false);
		 request.send(formData);
		 if(request.status == 200) {
			 var JSONResult = JSON.parse(request.responseText);
			 document.getElementById("correctExpensesInfo").textContent = "Poprawne: " + JSONResult.correctExpenses;
		     document.getElementById("incorrectExpensesInfo").textContent = "Niepoprawne: " + JSONResult.incorrectExpenses; 
		 }
	 }
 };
</script>

</body>
</html>