<%@page import="com.dxc.payroll.services.dto.IndexationDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="content">
	<section class="container">
		<h2>
			<fmt:message key="allIndexations" />
		</h2>
		<div>
			<table
				class="table 
									   table-striped 
									   table-bordered 
									   table-condensed 
									   table-hover">
				<thead class="thead-inverse">
					<tr>
						<th><fmt:message key="dateOfIndexation" /></th>
						<th><fmt:message key="position" /></th>
						<th><fmt:message key="jobLevel" /></th>
						<th><fmt:message key="percentage" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${indexationsList}" var="indexation">
						<tr>
							<td><c:out value="${indexation.dateOfIndexation}" /></td>
							<td><c:out value="${indexation.percentage}" /></td>
							<td><c:out
									value="${indexation.jobDegree} ${indexation.jobTitle}" /></td>
							<td><c:out value="${indexation.jobLevel}" /></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>

		</div>
	</section>
</c:set>

<%@include file="../layouts/main.jsp"%>