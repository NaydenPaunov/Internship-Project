<%@page import="com.dxc.payroll.services.dto.EmployeeDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Reuse employee info.-->
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
				<tr>
					<td><c:out value="${employee.name}" /></td>
					<td><c:out value="${employee.username}" /></td>
					<td><c:out value="${employee.previousWorkExperience}" /></td>
					<td><c:out value="${employee.contractType}" /></td>
					<td><c:out value="${employee.workHours}" /></td>
					<td><c:out value="${employee.teamLeadName}" /></td>
					<td><c:out value="${employee.roleName}" /></td>
					<td><fmt:formatNumber minFractionDigits="2"
							value="${employee.netSalary} " />
				</tr>
			</tbody>
		</table>
		<c:if test="${not empty employee}">
			<ul class="list-group">
				<li class="list-group-item"><a class="btn"
					href="paycheck?id=${employee.ucn}"><fmt:message
							key="showPaychecks" /></a></li>
				<li class="list-group-item"><a class="btn"
					href="subordinate?ucn=${employee.ucn}"><fmt:message
							key="showSubordinate" /></a></li>
				<li class="list-group-item"><a class="btn"
					href="currentPositionView?employeeUCN=${employee.ucn}"><fmt:message
							key="showCurrentPosition" /></a></li>
				<li class="list-group-item"><a class="btn"
					href="positionHistory?employeeUCN=${employee.ucn}"><fmt:message
							key="showPositionHistory" /></a></li>
				<li class="list-group-item"><a class="btn"
					href="raisingEmployee?employeeUCN=${employee.ucn}"><fmt:message
							key="raiseEmployee" /></a></li>
				<li class="list-group-item"><a class="btn"
					href="rights?emplId=${employee.ucn}"><fmt:message
							key="showRights" /></a></li>
			</ul>
		</c:if>
	</section>
</c:set>

<%@include file="../layouts/main.jsp"%>
