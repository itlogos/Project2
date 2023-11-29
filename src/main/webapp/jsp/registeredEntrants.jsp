<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Registered Entrants</title>

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
	<!-- Sidebar  -->
	<nav id="sidebar">
		<div class="sidebar-header">
			<h3>University</h3>
		</div>

		<ul class="list-unstyled components">
			<p>${pageContext.request.userPrincipal.name}</p>
			<li><a href="/home">Home</a></li>
			<li><a href="/create-faculty">Create faculty</a></li>
			<li class="active"><a href="/registeredEntrants">Registered Entrants</a></li>
		</ul>
	</nav>

	<!-- Header Content  -->
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
		
		<c:if test="${not empty registeredEntrants}">
			<table style="width: 100%; border: 2px solid black;"">
				<tr>
					<th>Photo</th>
					<th>First name</th>
					<th>Last name</th>
					<th>Email</th>
					<th>Faculty name</th>
					<th colspan="4">Marks</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${registeredEntrants}" var="currentRegisteredEntrant">
					<tr>
						<td rowspan="2"><img src="data:image/png;base64,${currentRegisteredEntrant.encodedEntrantImage}" width="70" height="70" /></td>
						<td rowspan="2">${currentRegisteredEntrant.user.firstName}</td>
						<td rowspan="2">${currentRegisteredEntrant.user.lastName}</td>
						<td rowspan="2">${currentRegisteredEntrant.user.email}</td>
						<td rowspan="2">${currentRegisteredEntrant.faculty.name}</td>
						
						<c:forEach items="${currentRegisteredEntrant.faculty.subjects}" var="currentSubject">
							<td>${currentSubject}</td>
						</c:forEach>
						
						<td rowspan="2">
							<form:form method="POST" action="${contextPath}/entrantSubmiting">						
								<input type="hidden" name="facultyId" value="${currentRegisteredEntrant.faculty.id}" />
								<input type="hidden" name="userId" value="${currentRegisteredEntrant.user.id}" />
								<input type="hidden" name="entrantId" value="${currentRegisteredEntrant.id}" />
								<button type="submit">Submit</button>
							</form:form>
						</td>
					</tr>
					
					<tr>
						<c:forEach items="${currentRegisteredEntrant.marks}" var="currentMark">
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