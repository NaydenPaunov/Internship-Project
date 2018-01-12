<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="content">
	<section class="container">
		<h2>
			Name:
			<c:out value="${name}" />
		</h2>
		<h2>
			UCN:
			<c:out value="${ucn}" />
		</h2>
		<table
			class="table 
				   table-striped 
				   table-bordered 
				   table-condensed 
				   table-hover">
			<thead class="thead-inverse">
				<tr>
					<th>Job Title</th>
					<th>Job Level</th>
					<th>Base Salary</th>
					<th>Start Date</th>
					<th>End Date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="record" items="${pHistory}">
					<tr>
						<td><a href="job?jobTitle=${record.jobTitle}"><c:out
									value="${record.jobTitle}" /></a></td>
						<td><c:out value="${record.jobLevel}" /></td>
						<td><fmt:formatNumber minFractionDigits="2"
								value="${record.baseSalary}" /></td>
						<td><c:out value="${record.startDate}" /></td>
						<td><c:out value="${record.endDate}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</c:set>

<%@include file="../layouts/main.jsp"%>