<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="content">
	<h2>
		<fmt:message key="successfullyRaised" />
	</h2>
</c:set>
<%@include file="../layouts/main.jsp"%>