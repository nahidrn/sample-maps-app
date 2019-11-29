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

		<div class="well lead">User Registration Form</div>
	 	<form:form method="POST" modelAttribute="user" class="form-horizontal" onsubmit="return submitForm()">
			<form:input type="hidden" path="id" id="id"/>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="id">User Name</label>
					<div class="col-md-7">
						<form:input type="text" path="name" id="name" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="name" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="password">Password</label>
					<div class="col-md-7">
						<form:input type="password" path="password" id="password" class="form-control input-sm" />
						<div class="has-error" id="password-error">
							<form:errors path="password" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>


			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="active">Active Status</label>
					<div class="col-md-7">
						<form:select path="active" id="active" class="form-control input-sm dropdown-big" >
							<form:option value="true">Active</form:option>
							<form:option value="false">Inactive</form:option>
						</form:select>
						<div class="has-error">
							<form:errors path="active" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>

	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="userProfiles">Roles</label>
					<div class="col-md-7">
						<form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="userProfiles" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/listusers' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/listusers' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</div>
</body>

<script>

	function submitForm() {
		var passwordError = document.getElementById("password-error");
		passwordError.innerHTML = "";
		console.log("@submitForm");

		var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");


		var password = document.getElementById("password").value;

		console.log("@password", password);
		console.log("@password", strongRegex.test(password));

		if (!strongRegex.test(password)) {
			passwordError.innerHTML = "Password must contain atleast one uppercase, one lowercase, one number, one special symbol and 8 characters";
			return false;
		} else {
			return true;
		}

	}

</script>

</html>