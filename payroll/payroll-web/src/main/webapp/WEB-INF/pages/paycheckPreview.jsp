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
					<th><fmt:message key="taxRate" /></th>
					<th><fmt:message key="netSalary" /></th>
					<th><fmt:message key="dateOfPaycheck" /></th>
					<th><fmt:message key="hoursWorked" /></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:out value="${salaryViewModel.baseSalary}" /></td>

					<td><c:out value="${salaryViewModel.grossSalary}" /></td>

					<td><c:out value="${salaryViewModel.taxRate}" /></td>

					<td><c:out value="${salaryViewModel.netSalary}" /></td>

					<td><c:out value="${salaryViewModel.dateOfPaycheck}" /></td>

					<td><c:out value="${salaryViewModel.hoursWorked}" /></td>
				</tr>
			</tbody>
		</table>

		<form method="Post">
			<!-- name-action -->
			<input type="submit" name="save" value="Save"> <input
				type="submit" name="cancel" value="Cancel">
		</form>
	</section>
	<!-- Send the paycheck dto, from PaycheckService with the net and gross salary algorithms added to the method ,here and display it -->
</c:set>

<%@include file="../layouts/main.jsp"%>