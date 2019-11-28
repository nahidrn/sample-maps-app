<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<%@include file="header.jsp" %>
<body>
	<div class="generic-container">
		<%@include file="authheader.jsp" %>
		
		<div class="alert alert-success lead">
	    	Successful
		</div>
		
		<span class="well floatRight">
			Go to <a href="<c:url value='/listusers' />">Users List</a>
		</span>
	</div>
</body>

</html>