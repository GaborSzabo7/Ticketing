package hu.otpmobile.ticketing.api.infrastructure.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@IntegrationComponentScan
@Configuration
@ImportResource(locations = { "classpath:/integration/ticket-intergration.xml", "classpath:/integration/core-intergration.xml" })
public class IntegrationConfiguration {

	@Bean
	public MessageChannel responseChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel errorChannel() {
		return new DirectChannel();
	}

}
