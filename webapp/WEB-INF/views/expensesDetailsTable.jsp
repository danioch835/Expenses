<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<table class="table">
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
<c:forEach items="${expensesDetails}" var="expense">
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
