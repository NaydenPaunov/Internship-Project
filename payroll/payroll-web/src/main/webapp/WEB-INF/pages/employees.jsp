<%@page import="com.dxc.payroll.services.dto.EmployeeDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
					<th><fmt:message key="employees" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${employees}" var="employee">
					<tr>
						<td><a href="employee?ucn=${employee.ucn}"><c:out
									value="${employee.name}" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</c:set>

<%@include file="../layouts/main.jsp"%>