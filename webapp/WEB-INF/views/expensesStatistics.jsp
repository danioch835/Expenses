<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

    <form method="POST">
        <select name="personName" id="nameSelect">
            <c:forEach items="${persons}" var="person" >
                <option>${person.name}</option>
            </c:forEach>
        </select>
    
        <fieldset>
            <c:forEach items="${categories}" var="category" >
            <c:set var="boxChecked" value="" />
            <c:forEach items="${selectedCategories}" var="selectedCategory" >
                <c:if test="${category.name eq selectedCategory}" >
                    <c:set var="boxChecked" value="checked" />
                </c:if>
            </c:forEach>
            <input type="checkbox" ${boxChecked} id="checkbox_${category.name}" name="categories" value="${category.name}" />
                <label for="checkbox_${category.name}">${category.name}</label>
            </c:forEach>
        </fieldset>
    
        <label for="expenseDateFrom" >Data from</label>
        <input id="expenseDateFrom" type="text" name="dateFrom" />
        <label for="expenseDateTo" >Data to</label>
        <input id="expenseDateTo" type="text" name="dateTo" />
        <input type="submit" value="Pokaz" />
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
    
