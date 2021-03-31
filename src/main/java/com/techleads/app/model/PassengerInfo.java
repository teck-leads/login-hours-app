package com.techleads.app.model;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class PassengerInfo {
	
	private String name;
	private String from ;
	private String to;
	private String jourenyDate;
	

}
