<%@page import="com.dxc.payroll.services.dto.IndexationDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
table, th, td {
    border: 1px solid black;
}
</style>

<title>Indexation</title>
</head>

<body>
	<h1>Indexation</h1>

	<table>
		<tr>
			<th>Percentage</th>
			<th>Date</th>
			<th>Job Title</th>
		</tr>
		<c:forEach items="${indexations}" var="indexation">   
		<tr>
			<td><c:out value="${indexation.percentage}" /></td>
			<td><c:out value="${indexation.dateOfIndexation}" /></td>
			<td><c:out value="${indexation.jobTitle}" /></td>
		</tr>
		</c:forEach>
	</table>

</body>
</html>