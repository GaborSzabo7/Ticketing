package hu.otpmobile.ticketing.api.infrastructure.integration.core;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import hu.otpmobile.ticketing.api.infrastructure.integration.core.request.ValidateBankcardRequest;

@MessagingGateway(name = "coreService", defaultReplyChannel = "responseChannel")
public interface CoreService {

	@Gateway(requestChannel = "validateTokenChannel", replyChannel = "responseChannel")
	boolean validateToken(String token);

	@Gateway(requestChannel = "validateBankcardChannel", replyChannel = "responseChannel")
	boolean validateBankcard(ValidateBankcardRequest request);

}
