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
<title>Edit Project</title>
</head>
<body>
	<div class="container-fluid">
		<div class="container mx-auto mt-4">
			<div class="text-end">
				<a href="/dashboard" class="nav-link mb-3">Go Back!</a>
			</div>
			<h1 class="display-4">Edit Project</h1>
			<form:form action="/projects/${project.id}/edit" modelAttribute="project" method="POST" class="col-5 mt-4 p-3">
				<input type="hidden" name="_method" value="PUT" />
				<div>
					<form:errors path="*" class="text-danger"/>
				</div>
				<div class="mb-3">
					<form:label path="title" >Project Title:</form:label>
					<form:input type="text" path="title"  class="form-control" />
				</div>
				<div class="mb-3">
					<form:label path="description">Project Description:</form:label>
					<form:textarea type="text" path="description"  class="form-control"></form:textarea>
				</div>
				<div class="mb-3">
					<form:label path="dueDate" >Due Date:</form:label>
					<form:input type="date" path="dueDate"  class="form-control" />
				</div>
				<button>Submit</button>
			</form:form>
			
		</div>
	</div>
</body>
</html>