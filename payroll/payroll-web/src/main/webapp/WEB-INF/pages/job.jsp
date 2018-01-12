<%@page import="com.dxc.payroll.services.dto.JobWithSalaryDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="content">
	<section class="container">
		<c:choose>
			<c:when test="${not empty errorMessage}">
				<p>${errorMessage}</p>
			</c:when>
			<c:otherwise>
				<%@include file="../section/jobComboBoxes.jsp"%>
				<c:if test="${not empty viewModel.jobList}">
					<table
						class="table 
				   table-striped
			       table-bordered 
				   table-condensed 
			       table-hover">
						<thead class="thead-inverse">
							<tr>
								<th><fmt:message key="job" /></th>
								<th><fmt:message key="minSalary" /></th>
								<th><fmt:message key="maxSalary" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${viewModel.jobList}" var="job" varStatus="i">
								<tr>
									<td><fmt:message
											key="${job.jobDegree}.${viewModel.selectedTitle}" /></td>
									<td><fmt:formatNumber minFractionDigits="2"
											value="${job.minSalary}" /></td>
									<td><fmt:formatNumber minFractionDigits="2"
											value="${job.maxSalary}" /></td>
								</tr>
								<c:if test="${not empty job.levelAndSalaryDTOs}">
									<tr>
										<td colspan="3">
											<div class="panel-group" id="accordion">
												<div class="panel panel-default">
													<div class="panel-heading">
														<h4 class="panel-title">
															<a data-toggle="collapse" data-parent="#accordion"
																href="#collapse${i.index}"><fmt:message
																	key="positions" /></a>
														</h4>
													</div>
													<div id="collapse${i.index}"
														class="panel-collapse collapse">
														<div class="panel-body">
															<%@include file="../section/levelAndSalaryTable.jsp"%>
														</div>
													</div>
												</div>
											</div>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
					<ul class="list-group">
						<li class="list-group-item"><a class="btn"
							href="addPosition?profession=${viewModel.selectedTitle}"><fmt:message
									key="addPosition" /></a></li>
						<li class="list-group-item"><a class="btn"
							href="indexation?jobTitle=${viewModel.selectedTitle}"><fmt:message
									key="showIndexations" /></a></li>
					</ul>
				</c:if>
			</c:otherwise>
		</c:choose>
	</section>
</c:set>

<%@include file="../layouts/main.jsp"%>