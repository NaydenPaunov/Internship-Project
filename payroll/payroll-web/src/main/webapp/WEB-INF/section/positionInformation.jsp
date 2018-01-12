<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<section class="container">
	<h2>
		<fmt:message key="employeeCurrentPosition" />
	</h2>
	<table
		class="table
				  table-striped 
				  table-bordered 
				  table-condensed 
				  table-hover">
		<thead class="thead-inverse">
			<tr>
				<th><fmt:message key="employeeName" /></th>
				<th><fmt:message key="jobTitle" /></th>
				<th><fmt:message key="positionDegree" /></th>
				<th><fmt:message key="baseSalary" /></th>
				<th><fmt:message key="positionLevel" /></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><c:out value="${viewModel.employeeName}" /></td>
				<td><a href="job?jobTitle=${viewModel.positionDTO.jobTitle}">
						<fmt:message key="${viewModel.positionDTO.jobTitle}" />
				</a></td>
				<td><fmt:message key="${viewModel.positionDTO.jobDegree}" /></td>
				<td>${viewModel.positionDTO.baseSalary}</td>
				<td>${viewModel.positionDTO.jobLevel}</td>
			</tr>
		</tbody>
	</table>
</section>