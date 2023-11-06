package hu.otpmobile.ticketing.api.infrastructure.integration.core.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidateBankcardRequest {

	private final String token;

	private final String cardId;

}
