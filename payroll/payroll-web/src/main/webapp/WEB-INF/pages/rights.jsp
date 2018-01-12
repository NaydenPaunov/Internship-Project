<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="content">
	<h1>Your rights</h1>
	<section class="container">
		<table
			class="table 
				   table-striped
			 	   table-bordered 
			 	   table-condensed 
			 	   table-hover">
			<thead class="thead-inverse">
				<tr>
					<th><fmt:message key="role" /></th>
					<th><fmt:message key="rights" /></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:out value="${viewModel.employee.roleName}" /></td>
					<td><c:forEach var="right" items="${viewModel.rights}">
							<c:out value="${right.description} " />
						</c:forEach></td>
				</tr>
			</tbody>
		</table>
	</section>
</c:set>

<%@include file="../layouts/main.jsp"%>