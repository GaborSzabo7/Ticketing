package hu.otpmobile.ticketing.ticket.infrastructure.intgeration.partner;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Payload;

import hu.otpmobile.ticketing.ticket.infrastructure.intgeration.partner.request.ReserveSeatRequest;
import hu.otpmobile.ticketing.ticket.infrastructure.intgeration.partner.request.SeatPriceRequest;
import hu.otpmobile.ticketing.ticket.infrastructure.intgeration.partner.response.ReserveResponse;
import hu.otpmobile.ticketing.ticket.model.EventData;
import hu.otpmobile.ticketing.ticket.model.EventSeatData;

@MessagingGateway(name = "partnerService", defaultReplyChannel = "responseChannel")
public interface PartnerService {

	@Gateway(requestChannel = "partnerEventsChannel", replyChannel = "responseChannel")
	@Payload("new java.lang.Object()")
	EventData getEvents();

	@Gateway(requestChannel = "partnerEventSeatsChannel", replyChannel = "responseChannel")
	EventSeatData getEventSeats(long eventId);

	@Gateway(requestChannel = "partnerReserveSeatChannel", replyChannel = "responseChannel")
	ReserveResponse reserveSeat(ReserveSeatRequest request);

	@Gateway(requestChannel = "partnerSeatPriceChannel", replyChannel = "responseChannel")
	int getSeatPrice(SeatPriceRequest request);

	@Gateway(requestChannel = "partnerEventStartTimeChannel", replyChannel = "responseChannel")
	long getEventStartTime(long eventId);
}
