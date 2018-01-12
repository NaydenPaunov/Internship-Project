<%@page import="com.dxc.payroll.services.dto.PaycheckDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
					<th><fmt:message key="baseSalary" /></th>
					<th><fmt:message key="grossSalary" /></th>
					<th><fmt:message key="netSalary" /></th>
					<th><fmt:message key="dateOfPaycheck" /></th>
					<th><fmt:message key="hoursWorked" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="paycheck" items="${paychecks}">
					<tr>
						<td><c:out value="${paycheck.baseSalary}" /></td>
						<td><c:out value="${paycheck.grossSalary}" /></td>
						<td><c:out value="${paycheck.netSalary}" /></td>
						<td><c:out value="${paycheck.dateOfPaycheck}" /></td>
						<td><c:out value="${paycheck.hoursWorked}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</c:set>

<%@include file="../layouts/main.jsp"%>