<%@page import="com.dxc.payroll.services.dto.PaycheckDTO"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<h1>Paycheck</h1>
<h2>UCN: <c:out value="${paychecks[0].employeeUCN}" /></h2>
<table>
	<thead>
	<tr>
		<th>Base salary</th>
		<th>Gross salary</th>
		<th>Net salary</th>
		<th>Date of paycheck</th>
		<th>Hours worked</th>
	</tr>
	<c:forEach var="paycheck" items="${paychecks}">
	<tr>
		<td><c:out value = "${paycheck.baseSalary}"/></td>
		<td><c:out value = "${paycheck.grossSalary}"/></td>
		<td><c:out value = "${paycheck.netSalary}"/></td>
		<td><c:out value = "${paycheck.dateOfPaycheck}"/></td>
		<td><c:out value = "${paycheck.hoursWorked}"/></td>
	</tr>
	</c:forEach>
	</thead>
</table>
</body>
</html>