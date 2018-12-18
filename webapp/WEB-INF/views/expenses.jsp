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

<form action='/Expenses/expenses' method='POST' >
  <select name="personName">
    <c:forEach items="${persons}" var="person">
      <c:if test="${person.name == 'a'}" >
        <c:set var="selectedParam" value="selected" />
      </c:if>
      <option selected="${selectedParam}" value="${person.name}">${person.name}</option>
    </c:forEach>
  </select>
</form>

<c:set var="expensesDetails" value="${expenses}" scope="request" />
<jsp:include page="expensesDetailsTable.jsp" />

</body>
</html>