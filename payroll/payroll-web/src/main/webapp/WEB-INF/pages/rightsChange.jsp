<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="content">
	<form method="post">
		<div class="container">
			<c:forEach var="role" items="${viewModel.mapping}">
				<p></p>
				<div style="width: 50px">
					<c:out value="${role.roleName}" />
					<c:forEach var="right" items="${role.rightMapping}">
						<div style="width: 300px">
							<c:out value="${right.description}" />
							<input type="checkbox" name="${role.roleName}:${right.rightName}"
								<c:if test="${right.checked}">   checked="checked"</c:if>
								style="float: right" />
						</div>
					</c:forEach>
				</div>
			</c:forEach>
			<input type="submit" value="Save" />
		</div>
	</form>
</c:set>

<%@include file="../layouts/main.jsp"%>