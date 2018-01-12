<%@page import="com.dxc.payroll.services.dto.TaxDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="content">
	<section class="container">
		<form method="post" action="updateTax">
			<table
				class="table 
				   table-striped
			       table-bordered
				   table-condensed 
				   table-hover">
				<thead class="thead-inverse">
					<tr>
						<th><fmt:message key="typeOfTax" /></th>
						<th><fmt:message key="percentageEmployee" /></th>
						<th><fmt:message key="percentageCompany" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="typeOfTax" varStatus="status" items="${taxes}">
						<tr>
							<td><c:out value="${typeOfTax.key}" /> <input type="hidden"
								name="typeOfTax" value="${typeOfTax.key}"></td>
							<td><input type="text" name="percentageEmployee"
								value="${typeOfTax.value[0]}" required></td>
							<td><input type="text" name="percentageCompany"
								value="${typeOfTax.value[1]}" required></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input type="submit" value=<fmt:message key = "generate"/>>
		</form>
	</section>
</c:set>
<%@include file="../layouts/main.jsp"%>
