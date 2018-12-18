<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div id="statistics" >
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
<table>
<thead>
<th>Miesiac-rok</th>
<th>Wydatek</th>
</thead>
<tbody>
<c:forEach items="${personMonthsExpenses.monthsExpenses}" var="expense">
<tr>
<td>${expense.month}-${expense.year}</td>
<td>${expense.expense}</td>
</tr>
</c:forEach>
</tbody>
</table>

<img src="data:image/jpeg;base64, ${chart}" />
<img src="data:image/jpeg;base64, ${chart2}" />
</div>

<script type="text/javascript">

$('#nameSelect').change(function() {
	var personName = $(this).val();
	window.location = "/Expenses/statistics/personMonthsExpenses?personName=" + personName;
}) 

</script>