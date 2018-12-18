<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:if test="${not empty expensesInMonths}">
<table>
<thead>
<th>Osoba</th>
<th>Wydatek</th>
<th>Miesiac</th>
<th>Rok</th>
</thead>
<tbody>
<c:forEach items="${expensesInMonths}" var="expense">
<tr>
<td>${expense.personName}</td>
<td>${expense.expense}</td>
<td>${expense.month}</td>
<td>${expense.year}</td>
</tr>
</c:forEach>
</tbody>
</table>
</c:if>

<c:if test="${not empty expensesCategoryStatistics}">
<table>
<thead>
<th>Osoba</th>
<th>Kategoria</th>
<th>Wydatek</th>
<th>Miesiac</th>
<th>Rok</th>
</thead>
<tbody>
<c:forEach items="${expensesCategoryStatistics}" var="statistic">
<tr>
<td>${statistic.personName}</td>
<td>${statistic.categoryName}</td>
<td>${statistic.expense}</td>
<td>${statistic.month}</td>
<td>${statistic.year}</td>
</tr>
</c:forEach>
</tbody>
</table>
</c:if>


<c:if test="${not empty expenses}">
<table>
<thead>
<th>Osoba wydajaca</th>
<th>Sklep</th>
<th>Miasto</th>
<th>Kategoria wydatku</th>
<th>Koszt</th>
<th>Zwrot procentowy</th>
<th>Zwrot</th>
<th>Osoba zwracajaca</th>
<th>Data</th>
</thead>
<tbody>
<c:forEach items="${expenses}" var="expense">
<tr>
<td>${expense.personName}</td>
<td>${expense.shopName}</td>
<td>${expense.cityName}</td>
<td>${expense.categoryName}</td>
<td>${expense.koszt}</td>
<td>${expense.zwrotProcent}</td>
<td>${expense.zwrot}</td>
<td>${expense.zwrotPersonName}</td>
<td>${expense.data}</td>
</tr>
</c:forEach>
</tbody>
</table>
</c:if>
</body>
</html>