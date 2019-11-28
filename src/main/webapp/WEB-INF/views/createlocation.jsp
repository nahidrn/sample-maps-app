<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<%@include file="header.jsp" %>

<body>
<div class="generic-container">
    <%@include file="authheader.jsp" %>

    <div class="well lead">Location Creation Form</div>
    <form:form method="POST" modelAttribute="location" class="form-horizontal">
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
                    <div class="has-error">
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
                    <div class="has-error">
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
                    <div class="has-error">
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
                    <div class="has-error">
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
                    <div class="has-error">
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
                    <div class="has-error">
                        <form:errors path="address.longitude" class="help-inline"/>
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
</body>
</html>