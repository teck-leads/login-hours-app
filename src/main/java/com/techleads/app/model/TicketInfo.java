package com.techleads.app.model;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class TicketInfo {
	
	private Integer ticketId;
	private String ticketStatus;
	private Double ticketPrice;

}
