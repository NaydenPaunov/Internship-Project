<%@page import="com.dxc.payroll.services.dto.IndexationDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!--TODO: accordion -->
<!--TODO: remove unncessary c:out -->
<!-- finish internalization -->
<!-- remove logic from jsp -->

<c:set var="content">
	<section class="container">
		<h2>
			Indexations for
			<c:out value="${jobTitleParam}" />
		</h2>
		<div>
			<c:forEach items="${indexationsList}" var="indexation" varStatus="i">
				<c:if
					test="${indexationsList[i.index].dateOfIndexation != indexationsList[i.index+1].dateOfIndexation}">
					<c:if test="${!status.last}">
						<c:out value="${indexation.dateOfIndexation}" />
						<table
							class="table 
									   table-striped 
									   table-bordered 
									   table-condensed 
									   table-hover">
							<thead class="thead-inverse">
								<tr>
									<th><fmt:message key="position" /></th>
									<th><fmt:message key="jobLevel" /></th>
									<th><fmt:message key="percentage" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${indexationsList}" var="indx">
									<tr>
										<c:if
											test="${indexation.dateOfIndexation == indx.dateOfIndexation}">
											<td><a
												href="job?jobTitle=${indx.jobTitle}&jobDegree=
												${indx.jobDegree}"><c:out
														value="${indx.jobDegree} ${indx.jobTitle}" /></a></td>
											<td><c:out value="${indx.jobLevel}" /></td>
											<td><c:out value="${indx.percentage}" /></td>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</c:if>
			</c:forEach>
		</div>
	</section>
</c:set>

<%@include file="../layouts/main.jsp"%>