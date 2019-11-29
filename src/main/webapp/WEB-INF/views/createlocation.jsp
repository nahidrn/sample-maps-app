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

        <div class="well lead">Location Creation Form</div>
        <form:form method="POST" modelAttribute="location" class="form-horizontal" onsubmit="return submitForm()">
            <form:input type="hidden" path="id" id="id"/>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-label" for="id">Location Name</label>
                    <div class="col-md-5">
                        <form:input type="text" path="name" id="name" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="name" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-label" for="id">Street</label>
                    <div class="col-md-5">
                        <form:input type="text" path="address.street" id="address.street" class="form-control input-sm" />
                        <div id="address.street-error" class="has-error">
                            <form:errors path="address.street" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-label" for="id">Number</label>
                    <div class="col-md-5">
                        <form:input type="text" path="address.number" id="address.number" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="address.number" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-label" for="id">Zip Code</label>
                    <div class="col-md-5">
                        <form:input type="text" path="address.zipCode" id="address.zipCode" class="form-control input-sm" />
                        <div id="address.zipCode-error" class="has-error">
                            <form:errors path="address.zipCode" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-label" for="id">City</label>
                    <div class="col-md-5">
                        <form:input type="text" path="address.city" id="address.city" class="form-control input-sm" />
                        <div id="address.city-error" class="has-error">
                            <form:errors path="address.city" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-label" for="id">Country</label>
                    <div class="col-md-5">
                        <form:input type="text" path="address.country" id="address.country" class="form-control input-sm" />
                        <div id="address.country-error" class="has-error">
                            <form:errors path="address.country" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-label" for="id">Latitude</label>
                    <div class="col-md-5">
                        <form:input type="text" path="address.latitude" id="address.latitude" class="form-control input-sm" />
                        <div id="address.latitude-error" class="has-error">
                            <form:errors path="address.latitude" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-label" for="id">Longitude</label>
                    <div class="col-md-5">
                        <form:input type="text" path="address.longitude" id="address.longitude" class="form-control input-sm" />
                        <div id="address.longitude-error" class="has-error">
                            <form:errors path="address.longitude" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-label" for="type">Location Type</label>
                    <div class="col-md-4">
                        <form:select path="type" id="type" class="form-control input-sm dropdown-big" >
                            <form:option value="POI">POI</form:option>
                            <form:option value="EVENT">EVENT</form:option>
                        </form:select>
                        <div class="has-error">
                            <form:errors path="type" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>



            <div class="row">
                <div class="form-actions floatRight">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/l/listlocations' />">Cancel</a>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Create" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/l/listlocations' />">Cancel</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
    </div>
</div>


<script>

	function submitForm() {
        var hasError = false;
		var street = document.getElementById("address.street");
		var streetError = document.getElementById("address.street-error");
		streetError.innerHTML = "";
        if(street.value == "") {
			streetError.innerHTML = "may not be empty";
			hasError = true;
        }

		var zipCode = document.getElementById("address.zipCode");
		var zipCodeError = document.getElementById("address.zipCode-error");
		zipCodeError.innerHTML = "";
		if(zipCode.value == "") {
			zipCodeError.innerHTML = "may not be empty";
			hasError = true;
		}

		var city = document.getElementById("address.city");
		var cityError = document.getElementById("address.city-error");
		cityError.innerHTML = "";
		if(city.value == "") {
			cityError.innerHTML = "may not be empty";
			hasError = true;
		}

		var country = document.getElementById("address.country");
		var countryError = document.getElementById("address.country-error");
		countryError.innerHTML = "";
		if(country.value == "") {
			countryError.innerHTML = "may not be empty";
			hasError = true;
		}

		var latitude = document.getElementById("address.latitude");
		var latitudeError = document.getElementById("address.latitude-error");
		latitudeError.innerHTML = "";
		if(latitude.value == "") {
			latitudeError.innerHTML = "may not be empty";
			hasError = true;
		}

		var longitude = document.getElementById("address.longitude");
		var longitudeError = document.getElementById("address.longitude-error");
		longitudeError.innerHTML = "";
		if(longitude.value == "") {
			longitudeError.innerHTML = "may not be empty";
			hasError = true;
		}



        if(hasError) {
        	return false;
        }

	}

</script>
</body>
</html>