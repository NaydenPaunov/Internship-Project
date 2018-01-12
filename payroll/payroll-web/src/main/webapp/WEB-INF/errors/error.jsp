<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="content">
	<section class="container">
		<strong><fmt:message key="${errorMessage}" /></strong>
		<p>Error: ${errorCode}</p>
	</section>
</c:set>

<%@include file="../layouts/main.jsp"%>