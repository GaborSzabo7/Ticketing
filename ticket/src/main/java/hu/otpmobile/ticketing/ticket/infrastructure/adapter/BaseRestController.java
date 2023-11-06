package hu.otpmobile.ticketing.ticket.infrastructure.adapter;

import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;

public class BaseRestController {

	public <R> Callable<ResponseEntity<R>> get(final Supplier<R> service) {
		return () -> ResponseEntity.ok(requireNonNull(service.get(), "service can't be null"));
	}

	public <R> Callable<ResponseEntity<R>> post(final Supplier<R> service) {
		return () -> ResponseEntity.ok(requireNonNull(service.get(), "service can't be null"));
	}

	public Callable<ResponseEntity<Void>> put(final Runnable service) {
		return () -> {
			service.run();
			return ResponseEntity.status(NO_CONTENT).build();
		};
	}

}
