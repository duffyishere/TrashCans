package org.duffy.domain;

import lombok.Data;

@Data
public class TrashCanVO {
	
	private int serialNumber;
	private String boroughName;
	private String roadName;
	private String detailLocation;
}
