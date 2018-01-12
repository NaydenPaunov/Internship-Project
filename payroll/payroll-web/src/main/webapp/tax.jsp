<%@page import="com.dxc.payroll.services.dto.TaxDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tax</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</head>
<body>
	<header class="panel-heading">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Payroll</a>
		</nav>
	</header>
	<section class="container">
	<table
			class="table table-striped table-bordered table-condensed table-hover">
			<thead class="thead-inverse">
				<tr>
					<th>Tax Name</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Percentage</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:out value="${tax.typeOfTax}"/></td>
					<td><c:out value="${tax.startDate}"/></td>
					<td><c:out value="${tax.endDate}"/></td>
					<td><c:out value="${tax.percentage}"/></td>
				</tr>
			</tbody>
		</table>
	
		</section>
</body>
</html>