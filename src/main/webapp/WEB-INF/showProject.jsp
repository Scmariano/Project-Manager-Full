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
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Project Details</title>
</head>
<body>
	<div class="container-fluid">
		<div class="container mx-auto mt-4">
		<h1>Project Details</h1>
			<a href="/dashboard" class="nav-link mb-3">Back to Dashboard</a>
			<main class="col-8 px-4 py-3 border border-1 border-pirmary rounded bg-light">
				<div>
					<p>Project: <c:out value="${project.title}" /></p>
					<p>Description: <c:out value="${project.description}" /></p>
					<p>Due Date:<fmt:formatDate value="${project.dueDate}" pattern="MMMM dd"/></p>
				</div>
				<div class= "text-end">
					<c:if test= "${project.lead.id == user.id}">
						<a href="/projects/${project.id}/delete" class="nav-link">Delete</a>
					</c:if>
				</div>
				<a href="/projects/${project.id}/tasks" class="nav-link mb-3">See tasks!</a>
			</main>
		</div>
	</div>
</body>
</html>