<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<%@include file="header.jsp" %>
<body>
<div class="col-md-12">
	<div class="col-md-2"></div>
	<div class="generic-container col-md-8">
		<%@include file="authheader.jsp" %>
		
		<div class="alert alert-success lead">
	    	Successful
		</div>
		
		<span class="well floatRight">
			 <a href="<c:url value='/' />">Go to Home</a>
		</span>
	</div>
</div>
</body>

</html>