<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<form action="/Expenses/statistics/personMonthCategoriesExpenses"
      method="GET">
<select name="personName" id="nameSelect">
  <c:forEach items="${persons}" var="person" >
      <c:choose>
        <c:when test="${person.name eq selectedName}" >
          <option selected="selected">${person.name}</option>
        </c:when>
        <c:otherwise>
          <option>${person.name}</option>
        </c:otherwise>
      </c:choose>
  </c:forEach>
</select>
<label for="month" >Month</label>
<input type="text" name="month" value="${month}" />
<label for="year" >Year</label>
<input type="text" name="year" value="${year}" />
<input type="submit" value="Pokaż" />
</form>
<table>
<thead>
<th>Kategoria</th>
<th>Wydatek</th>
</thead>
<tbody>
<c:forEach items="${personCategoriesExpenses.categoriesExpenses}" var="expense">
<tr>
<td>${expense.categoryName}</td>
<td>${expense.expense}</td>
</tr>
</c:forEach>
</tbody>
</table>

<img src="data:image/jpeg;base64, ${chart}" />
<img src="data:image/jpeg;base64, ${chart2}" />