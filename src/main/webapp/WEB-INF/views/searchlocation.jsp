<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<%@include file="header.jsp" %>
<body>
<div class="col-md-12">
    <div class="col-md-2"></div>
    <div class="generic-container col-md-8">
        <%@include file="authheader.jsp" %>
        <div class="row">
            <div class="form-group col-md-12">
                <div class="form-group col-md-6" style="padding-left:0px !important;">
                    <div class="panel panel-default">
                        <!-- Default panel contents -->
                        <div class="panel-heading"><span class="lead">Event Locations </span></div>
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Location Name</th>
                                <th>Address</th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${eventLocations}" var="eventLocation">
                                <tr>
                                    <td>
                                        <div class="radio">
                                            <label><input type="radio" name="eventLocation" value="${eventLocation.address.latitude}~${eventLocation.address.longitude}"></label>
                                        </div>
                                    </td>
                                    <td>${eventLocation.name}</td>
                                    <td>${eventLocation.address.street} ${eventLocation.address.number}, ${eventLocation.address.zipCode}, ${eventLocation.address.city}, ${eventLocation.address.country}</td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <div class="panel panel-default">
                        <!-- Default panel contents -->
                        <div class="panel-heading"><span class="lead">Point of Interests </span></div>
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Location Name</th>
                                <th>Address</th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${poiLocations}" var="poiLocation">
                                <tr>
                                    <td>
                                        <div class="radio">
                                            <label><input type="radio" class="zeromargin" name="poiLocation" value="${poiLocation.address.latitude}~${poiLocation.address.longitude}"></label>
                                        </div>
                                    </td>
                                    <td>${poiLocation.name}</td>
                                    <td>${poiLocation.address.street} ${poiLocation.address.number}, ${poiLocation.address.zipCode}, ${poiLocation.address.city}, ${poiLocation.address.country}</td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <div class="form-group col-md-4"></div>
                <div class="form-group col-md-4">
                    <button id="refreshMap" class="btn btn-block btn-primary btn-default">GO!</button>
                </div>
                <div class="form-group col-md-4"></div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <div id="map-canvas" style="height:500px; width:100%"></div>
            </div>
        </div>
        <sec:authorize access="hasRole('ADMIN')">
            <div class="well">
                <a class="centered" href="<c:url value='/l/createlocation' />">Add New Location</a>
            </div>
        </sec:authorize>
    </div>
</div>
</body>
<script>
	var map;

	function calculateAndDisplayRoute(map, directionsService, directionsDisplay, pointA, pointB) {

		console.log("@pointA", pointA);
		console.log("@pointA", pointA.lat());

		directionsService.route({
			origin: pointA,
			destination: pointB,
			travelMode: google.maps.TravelMode.DRIVING
		}, function (response, status) {

			console.log("@response", response);
			console.log("@status", status);


			if (status == google.maps.DirectionsStatus.OK) {

                var distance = response.routes[0].legs[0].distance.text;
                console.log("@distance", distance);
                var flyingDistance = haversine_distance(pointA, pointB).toFixed(2);
                response.routes[0].legs[0].end_address = "<b>Point of Interest: </b><br/>"+response.routes[0].legs[0].end_address +
                                                    "</br><b>Distances: "+"</b>"+
                                                    "</br><b>Driving Distance: "+distance+"</b>"+
                                                    "</br><b>Flying Distance: "+flyingDistance+" km</b>";

				response.routes[0].legs[0].start_address = "<b>Event Location: </b><br/>"+response.routes[0].legs[0].start_address +
					                                "</br><b>Distances: "+"</b>"+
                                                    "</br><b>Driving Distance: "+distance+"</b>"+
                                                    "</br><b>Flying Distance: "+flyingDistance+" km</b>";
				directionsDisplay.setDirections(response);

				new google.maps.Polyline({path: [pointA, pointB], map: map});

			} else {
				window.alert('Directions request failed due to ' + status);
			}
		});
	}

	function haversine_distance(mk1, mk2) {
		var R = 3958.8; // Radius of the Earth in miles
		var rlat1 = mk1.lat() * (Math.PI/180); // Convert degrees to radians
		var rlat2 = mk2.lat() * (Math.PI/180); // Convert degrees to radians
		var difflat = rlat2-rlat1; // Radian difference (latitudes)
		var difflon = (mk2.lng()-mk1.lng()) * (Math.PI/180); // Radian difference (longitudes)

		var d = 2 * R * Math.asin(Math.sqrt(Math.sin(difflat/2)*Math.sin(difflat/2)+Math.cos(rlat1)*Math.cos(rlat2)*Math.sin(difflon/2)*Math.sin(difflon/2)));
		return d;
	}

	function initialize(destLoc, srcLoc) {

		var mapOptions = {
			zoom: 8
		};
		map = new google.maps.Map(document.getElementById('map-canvas'),
			mapOptions);

		var origin_location = new google.maps.LatLng(srcLoc[0], srcLoc[1]),
			destination_location = new google.maps.LatLng(destLoc[0], destLoc[1]);


		var directionsService = new google.maps.DirectionsService;
		var directionsDisplay = new google.maps.DirectionsRenderer({
			map: map
		});

		calculateAndDisplayRoute(map, directionsService, directionsDisplay, origin_location, destination_location);
//		calculateAndDisplayRoute(map, directionsService, directionsDisplay, origin_location2, destination_location2);

	}
	console.log(document.getElementById("refreshMap"));
	document.getElementById("refreshMap").onclick = function() {
		var destinationLoc = document.querySelector('input[name="eventLocation"]:checked').value;
		var srcLoc = document.querySelector('input[name="poiLocation"]:checked').value;
		if(destinationLoc != null && srcLoc != null) {
			destinationLoc = destinationLoc.split('~');
			srcLoc = srcLoc.split('~');
			initialize(destinationLoc, srcLoc);
        }
	}



</script>
</html>