package com.techleads.app.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class LoginDetails {
	private Integer id;
	private String name;
	private String actualShift;
	private Date loginDate;
	private String loginTime;
	private String logOffTime;
	private String comments;

	

}
