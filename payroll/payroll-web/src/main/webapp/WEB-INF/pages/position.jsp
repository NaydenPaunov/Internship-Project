<%@page import="com.dxc.payroll.services.dto.LevelAndSalaryDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="content">
	<table
		class="table 
				   table-striped
			       table-bordered 
				   table-condensed 
			       table-hover">
		<thead class="thead-inverse">
			<tr>
				<th>Job</th>
				<th>Job Level</th>
				<th>Base Salary</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${jobList}" var="job">
				<tr>
					<td><c:out value="${job.jobDegree} ${selectedTitle}" /></td>
					<c:forEach items="${job.levelAndSalaryDTOs}" var="levelAndSalary">
						<td><c:out value="${levelAndSalary.jobLevel}" /></td>
						<td><fmt:formatNumber minFractionDigits="2"
								value="${levelAndSalary.baseSalary}" /></td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:set>

<%@include file="../layouts/main.jsp"%>