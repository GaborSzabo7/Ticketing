package hu.otpmobile.ticketing.core.infrastructure.util;

import static java.util.Objects.requireNonNull;
import static java.util.stream.StreamSupport.stream;

import java.util.Spliterator;
import java.util.stream.Stream;

public interface StreamUtil {

	static <T> Stream<T> sequentialStream(final Spliterator<T> spliterator) {
		requireNonNull(spliterator, "spliterator can't be null");
		return stream(spliterator, false);
	}

	static <T> Stream<T> parallelStream(final Spliterator<T> spliterator) {
		requireNonNull(spliterator, "spliterator can't be null");
		return stream(spliterator, true);
	}

	static <T> Stream<T> sequentialStream(final Iterable<T> iterable) {
		requireNonNull(iterable, "iterable can't be null");
		return stream(iterable.spliterator(), false);
	}

	static <T> Stream<T> parallelStream(final Iterable<T> iterable) {
		requireNonNull(iterable, "iterable can't be null");
		return stream(iterable.spliterator(), true);
	}
}
