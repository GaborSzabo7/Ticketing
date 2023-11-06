package hu.otpmobile.ticketing.ticket.infrastructure.exception;

import static lombok.AccessLevel.PUBLIC;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ErrorMessage {

	@JsonProperty("errorMessage")
	private final int errorCode;

	@JsonProperty("errorMessage")
	private final String errorMessage;

}
