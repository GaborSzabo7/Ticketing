package hu.otpmobile.ticketing.ticket.infrastructure.intgeration.core;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import hu.otpmobile.ticketing.ticket.infrastructure.intgeration.core.request.CanCoverRequest;

@MessagingGateway(name = "coreService", defaultReplyChannel = "responseChannel")
public interface CoreService {

	@Gateway(requestChannel = "coreCanCoverChannel", replyChannel = "responseChannel")
	boolean canCover(CanCoverRequest request);

}
