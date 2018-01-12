<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Position History</title>
<style>
	table {
    	border-collapse: collapse;
    	width: 100%;
	}

	th, td {
    	text-align: left;
    	padding: 8px;
	}

	tr:nth-child(even){background-color: #f2f2f2}
</style>
</head>
<body>
	<h2>Name: <c:out value="${name}"/></h2>
	<h2>UCN: <c:out value="${ucn}"/></h2>
	<table>
		<thead>
			<tr>
				<th>Job Title</th>
				<th>Job Level</th>
				<th>Base Salary</th>
				<th>Start Date</th>
				<th>End Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="record" items="${pHistory}">
				<tr>
					<td><c:out value="${record.jobTitle}"/></td>
					<td><c:out value="${record.jobLevel}"/></td>
					<td><c:out value="${record.baseSalary}"/></td>
					<td><c:out value="${record.startDate}"/></td>
					<td><c:out value="${record.endDate}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>