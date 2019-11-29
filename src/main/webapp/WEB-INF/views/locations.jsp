<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<%@include file="header.jsp" %>

<body>
<div class="col-md-12">
	<div class="col-md-2"></div>
	<div class="generic-container col-md-8">
		<%@include file="authheader.jsp" %>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading"><span class="lead">List of Locations </span></div>
			<table class="table table-hover">
				<thead>
				<tr>
					<th>Location Name</th>
					<th>Location Type</th>
					<th>House #</th>
					<th>Street</th>
					<th>Zip Code</th>
					<th>City</th>
					<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
						<th width="100"></th>
					</sec:authorize>
					<sec:authorize access="hasRole('ADMIN')">
						<th width="100"></th>
					</sec:authorize>

				</tr>
				</thead>
				<tbody>
				<c:forEach items="${locations}" var="location">
					<tr>
						<td>${location.name}</td>
						<td>${location.type}</td>
						<td>${location.address.number}</td>
						<td>${location.address.street}</td>
						<td>${location.address.zipCode}</td>
						<td>${location.address.city}</td>
						<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
							<td><a href="<c:url value='/l/edit-location-${location.id}' />" class="btn btn-success custom-width">edit</a></td>
						</sec:authorize>
						<sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/l/delete-location-${location.id}' />" class="btn btn-danger custom-width">delete</a></td>
						</sec:authorize>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<sec:authorize access="hasRole('ADMIN')">
			<div class="well">
				<a href="<c:url value='/l/createlocation' />">Add New Location</a>
			</div>
		</sec:authorize>
	</div>
</div>
</body>
</html>