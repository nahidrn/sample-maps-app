package com.mm.app.constants;

public enum LocationType {
	EVENT("EVENT"),
	POI("POI");

	String locationType;

	private LocationType(String locationType){

		this.locationType = locationType;
	}

	public String LocationType() {

		return locationType;
	}
}
