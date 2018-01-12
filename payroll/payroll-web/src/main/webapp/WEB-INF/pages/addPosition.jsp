<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Internaciolizaciq -->
<c:set var="content">
	<section class="container">
		<h2>
			Add position to <strong>${profession.jobTitle}</strong> profession.
		</h2>
		<br>
		<div class="panel-group" id="accordion">
			<c:forEach var="job" items="${profession.jobWithSalaryAddDTOs}"
				varStatus="status">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapse${status.index}"> ${job.jobDegree} ::: Min
								Salary: <fmt:formatNumber minFractionDigits="2"
									value="${job.minSalary}" /> ... Max Salary: <fmt:formatNumber
									minFractionDigits="2" value="${job.maxSalary}" />
							</a>
						</h4>
					</div>
					<div id="collapse${status.index}" class="panel-collapse collapse">
						<div class="panel-body">
							<table
								class="table table-striped table-bordered table-condensed table-hover">
								<thead class="thead-inverse">
									<tr>
										<th>Job Level</th>
										<th>Base Salary</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="jobLevelToBeAdded" value="1" />
									<c:set var="minSalaryToBeChecked" value="${job.minSalary}" />
									<c:forEach var="record" items="${job.levelAndSalaryDTOs}"
										varStatus="status">
										<tr>
											<td><c:out value="${record.jobLevel}" /></td>
											<td><fmt:formatNumber minFractionDigits="2"
													value="${record.baseSalary}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<c:if test="${minSalaryToBeChecked != job.maxSalary}">
								<form method="post" action="addPosition">
									<input type="hidden" name="jobTitle"
										value="${profession.jobTitle}" /> <input type="hidden"
										name="jobDegree" value="${job.jobDegree}" /> <input
										type="hidden" name="jobLevel" value="${job.jobLevelToBeAdded}" />
									<input type="number" name="baseSalary"
										placeholder="Enter Base Salary"
										min="${job.minSalaryToBeChecked}" max="${job.maxSalary}"
										step="0.01" required /> <input type="submit"
										value="Add Position" />
								</form>
								<p>
									The Base Salary must be between
									<fmt:formatNumber minFractionDigits="2"
										value="${job.minSalaryToBeChecked}" />
									and
									<fmt:formatNumber minFractionDigits="2"
										value="${job.maxSalary}" />
									Salary.
								</p>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</c:set>

<%@include file="../layouts/main.jsp"%>