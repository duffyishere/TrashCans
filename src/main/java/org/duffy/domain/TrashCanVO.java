package org.duffy.domain;

import lombok.Data;

@Data
public class TrashCanVO {
	
	private Long tno;
	private Long serialNumber;
	
	private Double lat;
	private Double lng;
	
	private String boroughName;
	private String roadName;
	private String detailLocation;
}
