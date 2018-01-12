<%@page import="com.dxc.payroll.services.dto.TaxDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:set var="content">
	<section class="container">
		<form action="/payroll/updateTax" method="get">
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
						<th><fmt:message key="percentageEmployee" /></th>
						<th><fmt:message key="percentageCompany" /></th>
						<th><fmt:message key="checkToUpdateTax" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tax" items="${currentTaxes}">
						<tr>
							<td><c:out value="${tax.typeOfTax}" /></td>
							<td><c:out value="${tax.startDate}" /></td>
							<td><c:out value="${tax.percentageEmployee}" /></td>
							<td><c:out value="${tax.percentageCompany}" /></td>
							<td><input type="checkbox" name="typeOfTax"
								value="${tax.typeOfTax}"> Update <BR></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input type="submit" value=<fmt:message key = "generate"/>>
		</form>
	</section>
</c:set>

<%@include file="../layouts/main.jsp"%>
