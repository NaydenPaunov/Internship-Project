<%@page import="com.dxc.payroll.services.dto.EmployeeDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--Reuse common code from employeeId.jsp  -->
<c:set var="content">
	<section class="container">
		<table
			class="table 
					  table-striped 
					  table-bordered 
				      table-condensed
					  table-hover">
			<thead class="thead-inverse">
				<tr>
					<th><fmt:message key="name" /></th>
					<th><fmt:message key="username" /></th>
					<th><fmt:message key="previousWorkExperience" /></th>
					<th><fmt:message key="contractType" /></th>
					<th><fmt:message key="workHours" /></th>
					<th><fmt:message key="teamLeadName" /></th>
					<th><fmt:message key="roleName" /></th>
					<th><fmt:message key="netSalary" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="subordinate" items="${subordinate}">
					<tr>
						<td><c:out value="${subordinate.name}" /></td>
						<td><c:out value="${subordinate.username}" /></td>
						<td><c:out value="${subordinate.previousWorkExperience}" /></td>
						<td><c:out value="${subordinate.contractType}" /></td>
						<td><c:out value="${subordinate.workHours}" /></td>
						<td><c:out value="${subordinate.teamLeadName}" /></td>
						<td><c:out value="${subordinate.roleName}" /></td>
						<td><fmt:formatNumber minFractionDigits="2"
								value="${subordinate.netSalary} " />
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</c:set>

<%@include file="../layouts/main.jsp"%>