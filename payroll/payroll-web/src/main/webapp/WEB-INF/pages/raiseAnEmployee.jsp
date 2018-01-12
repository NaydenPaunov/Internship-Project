<%@page import="com.dxc.payroll.services.dto.PositionDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="content">
	<%@include file="../section/positionInformation.jsp"%>

	<section class="container">
		<h2>
			<fmt:message key="raiseEmployeeTitle" />
		</h2>

		<form method="POST">
			<input type="hidden" name="employeeUCN"
				value="${viewModel.employeeUCN}" />
			<div class="form-group col-xs-2">
				<select class="combobox" name="jobDegreeAndLevel"
					id="jobDegreeAndLevel">
					<option disabled selected>-- select an option --</option>
					<c:forEach items="${viewModel.allowablePositions}"
						var="allowablePosition">
						<option
							value="${allowablePosition.jobDegree}:${allowablePosition.jobLevel}">
							<fmt:message key="${allowablePosition.jobDegree}" />
							${allowablePosition.jobLevel}
						</option>
					</c:forEach>
				</select>
			</div>
			<input type="submit" name="check" value="check" /> <input
				type="submit" name="update" value="update" />
		</form>

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
					<td>${viewModel.employeeName}</td>
					<td><a href="job?jobTitle=${viewModel.positionDTO.jobTitle}">
							<fmt:message key="${viewModel.positionDTO.jobTitle}" />
					</a></td>
					<td><c:if test="${not (chosenPosition.jobDegree eq null)}">
							<fmt:message key="${chosenPosition.jobDegree}" />
						</c:if></td>
					<td>${chosenPosition.baseSalary}</td>
					<td>${chosenPosition.jobLevel}</td>
				</tr>
			</tbody>
		</table>
	</section>
</c:set>

<%@include file="../layouts/main.jsp"%>