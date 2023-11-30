<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Homepage</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
	integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/home.css">

<!-- Font Awesome JS -->
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"
	integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ"
	crossorigin="anonymous"></script>
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"
	integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY"
	crossorigin="anonymous"></script>


<!-- jQuery CDN - Slim version (=without AJAX) -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<!-- Popper.JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
	integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
	crossorigin="anonymous"></script>
<!-- Bootstrap JS -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
	integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
	crossorigin="anonymous"></script>

</head>
<div class="wrapper">
	
	<nav id="sidebar">
		<div class="sidebar-header">
			<h3>University</h3>
		</div>

		<ul class="list-unstyled components">
			<p>${pageContext.request.userPrincipal.name}</p>
			<li class="active"><a href="/home">Home</a></li>
			<security:authorize access="hasRole('ADMIN')">
			<li><a href="/create-faculty">Create faculty</a></li>
			<li><a href="/registeredEntrants">Registered Entrants</a></li>
			</security:authorize>
		</ul>
	</nav>

	<div id="content">

		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">

				<button type="button" id="sidebarCollapse" class="btn btn-info">
					<i class="fas fa-align-left"></i> <span>Toggle Sidebar</span>
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
										<i class="fas fa-sign-out-alt"></i><span>Log out</span>
									</button>
								</form>
							</c:if></li>

					</ul>
				</div>
			</div>
		</nav>
		

		<div class="container">
			<div class="row">
				
				<c:if test="${not empty faculties}">
					<c:forEach items="${faculties}" var="currentFaculty">
						<div class="col-6 col-md-4">
							<div class="card">

							
								<div class="view view-cascade overlay">
									<img class="card-img-top" src="../img/faculty-logo.jpg"
										alt="Card image cap">
								</div>

								<!-- Card content -->
								<div class="card-body">

									<!-- Title -->
									<h4 class="card-title">${currentFaculty.name}</h4>
									<!-- Text -->
									<p class="card-text">Quantity of student:
										${currentFaculty.students}</p>
									<p class="card-text">
										List of subjects:<br>
									</p>
									<c:forEach items="${currentFaculty.subjects}"
										var="currentSubject">
										<p class="card-text">${currentSubject}</p>
									</c:forEach>
									<!-- Button -->
									<security:authorize access="hasRole('USER')">
									<a
										href="entrantRegistration?currentFacultyId=${currentFaculty.id}&currentUserEmail=${pageContext.request.userPrincipal.name}"
										class="btn btn-primary">Choose this faculty</a>
									<a
										href="rating?currentFacultyId=${currentFaculty.id}""
										class="btn btn-primary">Show rating</a>	
										</security:authorize>
										<security:authorize access="hasRole('ADMIN')">
									<a
										href="#"
										class="btn btn-primary">Delete this faculty</a>
									</security:authorize>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="../js/home.js"></script>
</body>

</html>