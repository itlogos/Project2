<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title><spring:message code="registeredentrants.title"></spring:message></title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/home.css">

<!-- Font Awesome JS -->
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"></script>
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"></script>


<!-- jQuery CDN - Slim version (=without AJAX) -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<!-- Popper.JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<!-- Bootstrap JS -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

</head>
<div class="wrapper">
	<!-- Sidebar  -->
	<nav id="sidebar">
		<div class="sidebar-header">
			<h3>
				<spring:message code="home.name"></spring:message>
			</h3>
		</div>

		<ul class="list-unstyled components">
			<p>${pageContext.request.userPrincipal.name}</p>
			<li><a href="/home"><spring:message code="home.homesidebar"></spring:message></a></li>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="/create-faculty"><spring:message
							code="home.createfaculty"></spring:message></a></li>
				<li class="active"><a href="/registeredEntrants"><spring:message
							code="home.registeredentrants"></spring:message></a></li>
			</security:authorize>
		</ul>
	</nav>

	<!-- Header Content  -->
	<div id="content">

		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">

				<button type="button" id="sidebarCollapse" class="btn btn-info">
					<i class="fas fa-align-left"></i> <span><spring:message
							code="home.togglesidebar"></spring:message></span>
				</button>
				<button class="btn btn-dark d-inline-block d-lg-none ml-auto"
					type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<i class="fas fa-align-justify"></i>
				</button>

				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav ml-auto">
						<li class="nav-item"><c:if
								test="${pageContext.request.userPrincipal.name != null}">
								<form id="logoutForm" method="POST"
									action="${contextPath}/logout">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<button type="button" class="btn btn-info"
										onclick="document.forms['logoutForm'].submit()">
										<i class="fas fa-sign-out-alt"></i><span><spring:message
												code="home.logout"></spring:message></span>
									</button>
								</form>
							</c:if></li>

					</ul>
				</div>
			</div>
		</nav>

		<c:if test="${not empty registeredEntrants}">
			<table style="width: 100%;">
				<tr>
					<th><spring:message code="registeredentrants.photo"></spring:message></th>
					<th><spring:message code="registeredentrants.firstname"></spring:message></th>
					<th><spring:message code="registeredentrants.lastname"></spring:message></th>
					<th><spring:message code="registeredentrants.email"></spring:message></th>
					<th><spring:message code="registeredentrants.faculty"></spring:message></th>
					<th colspan="4"><spring:message
							code="registeredentrants.marks"></spring:message></th>
					<th><spring:message code="registeredentrants.action"></spring:message></th>
				</tr>
				<c:forEach items="${registeredEntrants}"
					var="currentRegisteredEntrant">
					<tr>
						<td rowspan="2"><img
							src="data:image/png;base64,${currentRegisteredEntrant.encodedEntrantImage}"
							width="70" height="70" /></td>
						<td rowspan="2">${currentRegisteredEntrant.user.firstName}</td>
						<td rowspan="2">${currentRegisteredEntrant.user.lastName}</td>
						<td rowspan="2">${currentRegisteredEntrant.user.email}</td>
						<td rowspan="2">${currentRegisteredEntrant.faculty.name}</td>

						<c:forEach items="${currentRegisteredEntrant.faculty.subjects}"
							var="currentSubject">
							<td>${currentSubject}</td>
						</c:forEach>

						<td rowspan="2"><form:form method="POST"
								action="${contextPath}/entrantSubmiting">
								<input type="hidden" name="facultyId"
									value="${currentRegisteredEntrant.faculty.id}" />
								<input type="hidden" name="userId"
									value="${currentRegisteredEntrant.user.id}" />
								<input type="hidden" name="entrantId"
									value="${currentRegisteredEntrant.id}" />
								<button type="submit">
									<spring:message code="registeredentrants.submit"></spring:message>
								</button>
							</form:form></td>
					</tr>

					<tr>
						<c:forEach items="${currentRegisteredEntrant.marks}"
							var="currentMark">
							<td>${currentMark}</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>

</div>
<script type="text/javascript" src="../js/home.js"></script>
</body>
</html>