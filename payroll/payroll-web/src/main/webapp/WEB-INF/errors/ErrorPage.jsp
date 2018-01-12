<%@page import="com.dxc.payroll.services.dto.JobDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="content">
	<section class="container">
		<fmt:message key="${errorMessage}" />
	</section>
</c:set>

<%@include file="../layouts/main.jsp"%>