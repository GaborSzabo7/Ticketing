package hu.otpmobile.ticketing.api.application;

import org.springframework.stereotype.Service;

import hu.otpmobile.ticketing.api.adapter.response.EventData;
import hu.otpmobile.ticketing.api.adapter.response.EventSeatData;
import hu.otpmobile.ticketing.api.adapter.response.PaySeatResponse;
import hu.otpmobile.ticketing.api.infrastructure.exception.ApiException;
import hu.otpmobile.ticketing.api.infrastructure.integration.core.CoreService;
import hu.otpmobile.ticketing.api.infrastructure.integration.core.request.ValidateBankcardRequest;
import hu.otpmobile.ticketing.api.infrastructure.integration.ticket.TicketService;
import hu.otpmobile.ticketing.api.infrastructure.integration.ticket.request.PaySeatRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApiServiceImpl implements ApiService {

	private final TicketService ticketService;
	private final CoreService coreService;

	public ApiServiceImpl(TicketService ticketService, CoreService coreService) {
		this.ticketService = ticketService;
		this.coreService = coreService;
	}

	@Override
	public EventData getEvents(String token) {
		if (validateUserToken(token)) {
			return ticketService.getEvents();
		} else {
			throw new ApiException("User Token is not valid.");
		}
	}

	@Override
	public EventSeatData getEventSeats(String token, long eventId) {
		if (validateUserToken(token)) {
			return ticketService.getEventSeats(eventId);
		} else {
			throw new ApiException("User Token is not valid.");
		}
	}

	@Override
	public PaySeatResponse paySeat(String token, long eventId, long seat, String cardId) {
		if (validateUserToken(token)) {
			if (coreService.validateBankcard(new ValidateBankcardRequest(token, cardId))) {
				return ticketService.paySeat(new PaySeatRequest(eventId, seat, cardId));
			} else {
				throw new ApiException("Bankcard (" + cardId + ") doesn't belongs to this user (" + token + ")");
			}
		} else {
			throw new ApiException("User Token is not valid.");
		}
	}

	private boolean validateUserToken(final String token) {
		if (token == null || token.isEmpty()) {
			throw new ApiException("User Token is empty.");
		}

		return coreService.validateToken(token);
	}

}
