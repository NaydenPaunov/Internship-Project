<%@page import="com.dxc.payroll.services.dto.PaycheckDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="content">
	<head>
<link rel="stylesheet" type="text/css" href="css/generatePaycheck.css">
	</head>
	<div>
		<c:out value="${errorMessage}" />
	</div>
	<form class="generate-form" method="post">
		<!-- submitting data to the same page -->
		<div class="form-input">UCN</div>
		<input class="generate-input" type="text" name="UCN"
			placeholder="Insert UCN" pattern="[0-9]{10}" required>
		<div class="form-input">
			<fmt:message key="dateOfPaycheck" />
		</div>
		<input class="generate-input" type="date" name="dateOfPaycheck"
			pattern="([0-9]{2})\/([0-9]{2})\/([0-9]{4})" required>
		<div class="form-input">
			<fmt:message key="hoursWorked" />
		</div>
		<input class="generate-input" type="number" name="hoursWorked"
			placeholder="Hours worked" pattern="[1-9][0-9]+" required> <input
			class="generate-submit" type="submit"
			value=<fmt:message key = "generate"/>>
	</form>
</c:set>

<%@include file="../layouts/main.jsp"%>