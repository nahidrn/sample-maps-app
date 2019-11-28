<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Location Creation Form</title>

    <style>

        .error {
            color: #ff0000;
        }
    </style>

</head>

<body>

<h2>Registration Form</h2>

<form:form method="POST" modelAttribute="location">
    <form:input type="hidden" path="id" id="id"/>
    <table>
        <tr>
            <td><label for="name">Name: </label> </td>
            <td><form:input path="name" id="name"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="address.street">Street: </label> </td>
            <td><form:input path="address.street" id="address.street"/></td>
            <td><form:errors path="address.street" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="address.number">Number: </label> </td>
            <td><form:input path="address.number" id="address.number"/></td>
            <td><form:errors path="address.number" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="address.street">Zip Code: </label> </td>
            <td><form:input path="address.zipCode" id="address.zipCode"/></td>
            <td><form:errors path="address.zipCode" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="address.city">City: </label> </td>
            <td><form:input path="address.city" id="address.city"/></td>
            <td><form:errors path="address.city" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="address.country">Country: </label> </td>
            <td><form:input path="address.country" id="address.country"/></td>
            <td><form:errors path="address.country" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="address.latitude">Latitude: </label> </td>
            <td><form:input path="address.latitude" id="address.latitude"/></td>
            <td><form:errors path="address.latitude" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="address.longitude">Longitude: </label> </td>
            <td><form:input path="address.longitude" id="address.longitude"/></td>
            <td><form:errors path="address.longitude" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="type">Type: </label> </td>
            <td>
                <form:select path="type" id="isActive">
                    <form:option value="POI">Point of Interest</form:option>
                    <form:option value="EVENT">Location of Event</form:option>
                </form:select>
            </td>
            <td><form:errors path="type" cssClass="error"/></td>
        </tr>

        <tr>
            <td colspan="3">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update"/>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Create"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
</form:form>
<br/>
<br/>
Go back to <a href="<c:url value='/locations' />">List of Locations</a>
</body>
</html>