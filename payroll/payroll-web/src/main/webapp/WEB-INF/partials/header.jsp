<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="com.dxc.payroll.properties.PayrollBundle"
	scope="session" />

<header class="panel-heading">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
				<li><a href="home"><fmt:message key="home" /></a></li>
				<li><a href="job"><fmt:message key="jobs" /></a></li>
				<li><a href="employees"><fmt:message key="employees" /></a></li>
				<li><a href="generatePaycheck"><fmt:message key="paychecks" /></a></li>
				<li><a href="currentTax"><fmt:message key="taxes" /></a></li>
				<li><a href="rightsChange"><fmt:message key="rightsChange" /></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<form id="bootstrapSelectForm" class="form-horizontal">
						<div class="form-group">
							<div class="col-xs-12 selectContainer">
								<select class="form-control" id="language" name="language"
									onchange="submit()">
									<option value="en_US" ${language == 'en_US' ? 'selected' : ''}>English</option>
									<option value="bg_BUL"
										${language == 'bg_BUL' ? 'selected' : ''}>Bulgarian</option>
								</select>
							</div>
						</div>
					</form>
				</li>
			</ul>
		</div>
	</nav>
</header>