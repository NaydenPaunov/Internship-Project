<%@page import="com.dxc.payroll.services.dto.TaxDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
					<th>Tax Name</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Percentage for the employee</th>
					<th>Percentage for the employer</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:out value="${tax.typeOfTax}" /></td>
					<td><c:out value="${tax.startDate}" /></td>
					<td><c:out value="${tax.endDate}" /></td>
					<td><c:out value="${tax.percentageEmployee}" /></td>
					<td><c:out value="${tax.percentageCompany}" /></td>
				</tr>
			</tbody>
		</table>
	</section>
</c:set>

<%@include file="../layouts/main.jsp"%>