<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row-fluid">
	<div class="pull-left">
		<form method="GET">
			<label><fmt:message key="jobTitle" />: </label> <select
				class="combobox" name="jobTitle" id="jobTitle"
				onchange="this.form.submit()">
				<option disabled selected><fmt:message key="selectJobTitle" /></option>
				<c:forEach items="${viewModel.jobTitles}" var="jobTitle">
					<option value="${jobTitle}"
						<c:if test="${jobTitle eq viewModel.selectedTitle}">
					selected
					</c:if>><fmt:message
							key="${jobTitle}" /></option>

				</c:forEach>
			</select>
		</form>
	</div>
	<div class="col-xs-offset-3">
		<form method="GET">
			<label><fmt:message key="jobDegree" />: </label> <input
				type="hidden" name="jobTitle" value="${viewModel.selectedTitle}">
			<select class="combobox" name="jobDegree" id="jobDegree"
				onchange="this.form.submit()">
				<option value=""><fmt:message key="selectJobDegree" /></option>
				<c:forEach items="${viewModel.comboBoxes.dtos[0].jobDegrees}"
					var="jobDegree">
					<option value="${jobDegree}"
						<c:if test="${jobDegree eq viewModel.selectedDegree}">
						selected
						</c:if>><fmt:message
							key="${jobDegree}" /></option>
				</c:forEach>
			</select>
		</form>
	</div>
</div>