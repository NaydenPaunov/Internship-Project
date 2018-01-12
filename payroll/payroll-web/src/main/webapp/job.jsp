<%@page import="com.dxc.payroll.services.dto.JobDTO"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="header.jsp"%>

<section class="container">
	<table
		class="table table-striped table-bordered table-condensed table-hover">
		<thead class="thead-inverse">
			<tr>
				<th>Job Title</th>
				<th>Min Salary</th>
				<th>Max Salary</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${jobList}" var="job">
				<tr>
					<td><c:out value="${job.jobTitle}" /></td>
					<td><c:out value="${job.minSalary}" /></td>
					<td><c:out value="${job.maxSalary}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
<%@include file="footer.jsp"%>