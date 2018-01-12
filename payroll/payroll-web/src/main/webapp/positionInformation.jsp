<%@page import="com.dxc.payroll.services.dto.PositionDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Position Information</title>
</head>
<body>
	<table style="width: 30%; border: 1px solid black;">
		<tr>
			<th colspan="2">Position ID: <%=(String) request.getAttribute("posId")%>
			</th>
		</tr>
		<tr>
			<td>Job Title:</td>
			<td><c:out value="${positionDTO.jobTitle}" /></td>
		</tr>
		<tr>
			<td>Base Salary:</td>
			<td><c:out value="${positionDTO.baseSalary}" /></td>
		</tr>
		<tr>
			<td>Position Level:</td>
			<td><c:out value="${positionDTO.positionLevel}" /></td>
		</tr>
	</table>
</body>
</html>