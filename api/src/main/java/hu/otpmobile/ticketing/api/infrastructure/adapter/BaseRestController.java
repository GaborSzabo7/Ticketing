package hu.otpmobile.ticketing.api.infrastructure.adapter;

import static java.util.Objects.requireNonNull;

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

}
