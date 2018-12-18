<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<form action="/Expenses/statistics/personCategoriesMonthExpenses"
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
<select name="categoryName" id="categoryNameSelect">
  <c:forEach items="${categories}" var="category" >
      <c:choose>
        <c:when test="${category.name eq categoryName}" >
          <option selected="selected">${category.name}</option>
        </c:when>
        <c:otherwise>
          <option>${category.name}</option>
        </c:otherwise>
      </c:choose>
  </c:forEach>
</select>
<input type="submit" value="Pokaż" />
</form>
<table>
<thead>
<th>Kategoria</th>
<th>Wydatek</th>
</thead>
<tbody>
<c:forEach items="${personMonthsExpenses.monthsExpenses}" var="expense">
<tr>
<td>${expense.month}</td>
<td>${expense.year}</td>
<td>${expense.expense}</td>
</tr>
</c:forEach>
</tbody>
</table>

<img src="data:image/jpeg;base64, ${chart}" />
<img src="data:image/jpeg;base64, ${chart2}" />