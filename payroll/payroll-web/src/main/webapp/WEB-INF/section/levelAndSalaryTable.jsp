<%@page import="com.dxc.payroll.services.dto.LevelAndSalaryDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table
	class="table 
			   table-striped
		       table-bordered 
			   table-condensed 
		       table-hover">
	<thead class="thead-inverse">
		<tr>
			<th><fmt:message key="jobLevel" /></th>
			<th><fmt:message key="baseSalary" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${job.levelAndSalaryDTOs}" var="levelAndSalary">
			<tr>
				<td>${levelAndSalary.jobLevel}</td>
				<td><fmt:formatNumber minFractionDigits="2"
						value="${levelAndSalary.baseSalary}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>