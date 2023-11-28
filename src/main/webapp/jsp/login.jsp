<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
<title>Login Page</title>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="../css/login.css">
</head>
<body>

<div class="text-center" style="padding:50px 0;width: 20%;margin: 0 auto;">
	<div class="logo">login</div>
	<!-- Main Form -->
	<div class="login-form-1">
		<form method="POST" action="${contentPath}/login" id="login-form" class="text-left form-signin">
			<div class="login-form-main-message"></div>
			<div class="main-login-form">
				<div class="login-group ${error != null ? 'has-error' : ''}">
				<span>${message}</span>
					<div class="form-group">
						<label for="lg_username" class="sr-only">Username</label>
						<input type="text" class="form-control" id="lg_username" name="email" placeholder="username">
					</div>
					<div class="form-group">
						<label for="lg_password" class="sr-only">Password</label>
						<input type="password" class="form-control" id="lg_password" name="password" placeholder="password">
					</div>
		                 <span>${error}</span>
                         <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</div>
				<button type="submit" class="btn btn-primary btn-lg btn-block pad-auth-btn">Sign in</button>
			</div>
			<div class="etc-login-form">
				<p>new user? <a href="${contextPath}/registration">create new account</a></p>
			</div>
		</form>
	</div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>