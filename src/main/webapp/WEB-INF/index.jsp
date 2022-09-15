<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<title>Login and Registration</title>
</head>
<body>
	<header>
		<div class="navbar navbar-dark bg-primary box-shadow ">
			<div class="container d-flex justify-content-center">
				<h1>Project Manager</h1>				
			</div>
			<div class="container d-flex justify-content-center">
				<p> A place for teams to manage projects.</p>			
			</div>
		</div>
	</header>
	
	<div class="container mt-4">
		<div class="row">
			<form:form  class="col bg-dark m2 p-2 round" action="/register" method="POST" modelAttribute = "newUser">
				<h2 class="text-white">Register</h2>
				<div>
					<form:errors path="*" class="text-danger"/>
				</div>
					<div class="form-group">
						<form:label path="firstName" class="text-white">First Name:</form:label>
						<form:input path="firstName" class="form-control mb-3"/>
					</div>
					<div class="form-group">
						<form:label path="lastName" class="text-white">Last Name:</form:label>
						<form:input path="lastName" class="form-control mb-3"/>
					</div>
					<div class="form-group">
						<form:label path="email" class="text-white">Email:</form:label>
						<form:input path="email" class="form-control mb-3"/>
					</div>
					<div class="form-group">
						<form:label path="password" class="text-white">Password:</form:label>
						<form:input path="password" type= "password" class="form-control mb-3"/>
					</div>
					<div class="form-group">
						<form:label path="confirm" class="text-white">Confirm Password:</form:label>
						<form:input path="confirm" type="password" class="form-control mb-3"/>
					</div>
					<button>Create</button>
			</form:form>

			<form:form  class="col bg-dark m-2 p-2 round" action="/login" method="Post" modelAttribute="newLogin">
					<h2 class="text-white">Login</h2>
					<div>
						<form:errors path="*" class="text-danger"/>
					</div><br/>
					<div class="form-group">
						<form:label path="email" class="text-white"> Email:</form:label>
						<form:input path="email"  class="form-control mb-3"/>
					</div><br/>
					<div class="form-group">
						<form:label path="password" class="text-white"> Password:</form:label>
						<form:input path="password" type="password"  class="form-control mb-3"/>
					</div><br/>
					<button>Login</button>
				</form:form>
		</div>
	</div>

</body>
</html>