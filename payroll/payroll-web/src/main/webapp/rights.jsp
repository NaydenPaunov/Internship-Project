<%@page import="com.dxc.payroll.services.dto.RightsDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
				</div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#">Jobs</a></li>
					<li><a href="#">Employees</a></li>
					<li><a href="#">Paychecks</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<section class="container">
		<table
			class="table table-striped table-bordered table-condensed table-hover">
			<thead class="thead-inverse">
				<tr>
					<th>Right Name</th>
					<th>Right</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:out value="${rights.name}" /></td>
					<td><c:out value="${rights.description}" /></td>
				</tr>
			</tbody>
		</table>
	</section>
	<footer class="navbar-fixed-bottom">
		<div class="container text-center">DXC</div>
	</footer>
</body>
</html>