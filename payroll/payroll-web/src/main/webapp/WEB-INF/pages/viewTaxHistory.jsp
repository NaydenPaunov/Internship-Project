<%@page import="com.dxc.payroll.services.dto.TaxDTO"%>
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
					<th><fmt:message key="typeOfTax" /></th>
					<th><fmt:message key="startDate" /></th>
					<th><fmt:message key="endDate" /></th>
					<th><fmt:message key="percentageEmployee" /></th>
					<th><fmt:message key="percentageCompany" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tax" items="${taxes}">
					<tr>
						<td><c:out value="${tax.typeOfTax}" /></td>
						<td><c:out value="${tax.startDate}" /></td>
						<td><c:out value="${tax.endDate}" /></td>
						<td><c:out value="${tax.percentageEmployee}" /></td>
						<td><c:out value="${tax.percentageCompany}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</c:set>

<%@include file="../layouts/main.jsp"%>
