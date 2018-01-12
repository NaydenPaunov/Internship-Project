<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="content">
	<section class="container">
		<div class="jumbotron">
			<div class="container text-center">
				<h1>
					<fmt:message key="applicationName" />
				</h1>
				<p>
					<fmt:message key="license" />
				</p>
			</div>
		</div>
	</section>
</c:set>
<%@include file="../layouts/main.jsp"%>