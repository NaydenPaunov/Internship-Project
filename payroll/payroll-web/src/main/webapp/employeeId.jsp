<%@page import="com.dxc.payroll.services.dto.EmployeeDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
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
					<th>UCN</th>
					<th>Name</th>
					<th>Username</th>
					<th>Previous work experience</th>
					<th>Contract type</th>
					<th>Work Hours</th>
					<th>Team Lead UCN</th>
					<th>Role Name</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:out value="${ucn}"/></td>
					<td><c:out value="${name}"/></td>
					<td><c:out value="${username}"/></td>
					<td><c:out value="${previousWorkExp}"/></td>
					<td><c:out value="${contractType}"/></td>
					<td><c:out value="${workHours}"/></td>
					<td><c:out value="${teamLeadUCN}"/></td>
					<td><c:out value="${roleName}"/></td>
				</tr>
			</tbody>
		</table>
	</section>
</body>
</html>