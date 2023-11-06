package hu.otpmobile.ticketing.partner.infrastructure.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ErrorMessage {

	@JsonProperty("errorCode")
	private final int errorCode;

	@JsonProperty("errorMessage")
	private final String message;

}
