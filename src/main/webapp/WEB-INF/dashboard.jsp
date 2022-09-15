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
<title>Project Manager Dashboard</title>
</head>
<body>

	<div class="container-fluid">
		<div class="container mx-auto mt-4">
			<header class="row justify-content-between align-items-center">
				<div class="col-5 text-start">
					<h4>Welcome, ${user.firstName}!</h4>
				</div>
				<div class="col-5 text-end">
					<a href="/logout" class="nav-link">Logout</a>
					<a href="/projects/new" class="nav-link">+Add new project</a>
				</div>
			</header>
		</div>
		<div class="row mx-auto mt-3">
			<h4>All Projects</h4>
			<table class="table table-striped table-bordered caption-top">
				<thead class="table-info">
					<tr class="align-middle text-center">
						<th>Project</th>
						<th>Team Lead</th>
						<th>Due Date</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="project" items="${unAssignedProjects}">
						<tr>
							<td><a href="/projects/${project.id}" class="nav-link"><c:out value="${project.title}" /></a></td>
							<td><c:out value="${project.lead.firstName}" /></td>
							<td><fmt:formatDate value="${project.dueDate}" pattern="MMMM dd"/></td>
							<td class="text-center">
								<c:if test="${project.lead.id != user.id}">
									<a href="/dashboard/${project.id}/join" class="nav-link">Join Team</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table >
			<h4>Your Projects</h4>
			<table class="table table-striped table-bordered caption-top">
				<thead class="table-info">
					<tr class="align-middle text-center">
						<th>Project</th>
						<th>Team Lead</th>
						<th>Due Date</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="project" items="${assignedProjects}">
						<tr>
							<td><a href="/projects/${project.id}" class="nav-link"><c:out value="${project.title}" /></a></td>
							<td><c:out value="${project.lead.firstName}" /></td>
							<td><fmt:formatDate value="${project.dueDate}" pattern="MMMM dd"/></td>
							<td class="text-center">
								<c:if test="${project.lead.id == user.id}">
									<a href="/projects/${project.id}/edit" class="nav-link">Edit</a>
								</c:if>
								<c:if test="${project.lead.id != user.id}">
									<a href="/dashboard/${project.id}/leave" class="nav-link">Leave Team</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	</div>
	

</body>
</html>