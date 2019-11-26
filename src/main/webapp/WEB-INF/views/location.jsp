<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Location Page</title>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #ccc;
	cellpadding: 10;
	cellspacing: 10;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	cellpadding: 10;
	cellspacing: 10;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font -weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
	cellpadding: 10;
	cellspacing: 10;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}

a.button {
	background-color: #4CAF50; /* Green */
	border: none;
	color: white;
	padding: 10px 15px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 15px;
	margin: 10px 15px 10px 8px;
}

a.btn {
	background-color: #f44336;
	border: none;
	color: white;
	padding: 10px 15px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 15px;
	margin: 10px 15px 10px 8px;
}

.table {
	border: 1px;
}

.th {
	width: 10%;
}

.value {
	margin: 10px 15px 10px 8px;
}
</style>
</head>
<body>
	<h3>Location List</h3>
	<c:if test="${!empty getLocationList}">
		<table class="tg">
			<tr>
				<th width="80">ID</th>
				<th width="120">Location Name</th>
			</tr>
			<c:forEach items="${getLocationList}" var="location">
				<tr>
					<td>${location.id}</td>
					<td>${location.name}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>