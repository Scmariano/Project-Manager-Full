<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>New Task</title>
</head>
<body>
	<div class="container-fluid">	
		<div class="container mx-auto mt-4">
			<p><a href="/dashboard">Dashboard</a></p>
			<h1>Project: ${project.title}</h1>
			<h5>Project Lead: ${project.lead.firstName}</h5>
			<main class="col-8 px-4 py-3 border border-1 border-pirmary rounded bg-light">
				<form:form action="/projects/${project.id}/tasks" method="post" modelAttribute="task">
					<div>
						<form:errors path="*" class="text-danger"/>
					</div>
					<div class="mb-3">
						<form:label path="text">Add a task ticket for this team:</form:label>
						<form:textarea type="text" path="text" class="form-control"></form:textarea>
					</div>
					<button>Submit</button>
				</form:form>
				<hr>
				<c:forEach var="task" items="${project.tasks}">
					<h4>Added by <c:out value="${task.creator.firstName}"></c:out> at <fmt:formatDate value="${task.createdAt}" pattern="h:mm a MMMM dd"/>:</h4>
					<p><c:out value="${task.text}"></c:out></p>
				</c:forEach>
			</main>
		</div>
	</div>
</body>
</html>