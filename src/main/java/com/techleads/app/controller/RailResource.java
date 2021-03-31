package com.techleads.app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techleads.app.model.PassengerInfo;
import com.techleads.app.model.TicketInfo;

@RestController
public class RailResource {

	@PostMapping(value = {"/bookticket"}, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	public TicketInfo bookTicket(@RequestBody PassengerInfo passengerInfo) {
		System.out.println(passengerInfo);

		TicketInfo ticketInfo = new TicketInfo();
		ticketInfo.setTicketId(123);
		ticketInfo.setTicketStatus("CNF");
		ticketInfo.setTicketPrice(500.00);
		return ticketInfo;
	}
}
