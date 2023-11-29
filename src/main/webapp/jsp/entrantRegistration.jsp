<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Entrant Registration</title>
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body>
	<!-- Sidebar -->
	<div class="w3-sidebar w3-light-grey w3-bar-block" style="width: 10%">
		<h3 class="w3-bar-item">Menu</h3>
		<a href="/home" class="w3-bar-item w3-button">Home</a>
		<a href="/create-faculty" class="w3-bar-item w3-button">Create Faculty</a>
		<a href="/rating" class="w3-bar-item w3-button">Rating</a>
	</div>

	<!-- Page Content -->
	<div style="margin-left: 10%">
		<div class="w3-container w3-teal">
			<h1>Entrant Registration</h1>
		</div>

		<div class="w3-container">
			<form:form method="POST" action="${contextPath}/addMarks" modelAttribute="statement">
				<table>
					<tr>
						<th>Enter your marks: </th>
					</tr>

					<c:forEach items="${statement.faculty.subjects}" var="currentSubject">
						<tr>
							<td><form:label path="marks">${currentSubject}</form:label></td>
							<td><form:input path="marks" /></td>
						</tr>
					</c:forEach>

					<tr>
						<td><form:input type="hidden" path="facultyId" value="${statement.faculty.id}" /></td>
						<td><form:input type="hidden" path="userEmail" value="${statement.user.email}" /></td>
					</tr>
					
					<tr>
						<td><input type="submit" value="Register" /></td>
					</tr>

				</table>
				
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form:form>
		</div>
	</div>

	<!-- /container -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>