package hu.otpmobile.ticketing.api.infrastructure.integration.ticket;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Payload;

import hu.otpmobile.ticketing.api.adapter.response.EventData;
import hu.otpmobile.ticketing.api.adapter.response.EventSeatData;
import hu.otpmobile.ticketing.api.adapter.response.PaySeatResponse;
import hu.otpmobile.ticketing.api.infrastructure.integration.ticket.request.PaySeatRequest;

@MessagingGateway(name = "ticketService", defaultReplyChannel = "responseChannel")
public interface TicketService {

	@Gateway(requestChannel = "eventsChannel", replyChannel = "responseChannel")
	@Payload("new java.lang.Object()")
	EventData getEvents();

	@Gateway(requestChannel = "eventSeatsChannel", replyChannel = "responseChannel")
	EventSeatData getEventSeats(long eventId);

	@Gateway(requestChannel = "paySeatChannel", replyChannel = "responseChannel")
	PaySeatResponse paySeat(PaySeatRequest request);

}
