package hu.otpmobile.ticketing.core.infrastructure.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(CoreException.class)
	@ResponseStatus(value = INTERNAL_SERVER_ERROR)
	public ErrorMessage coreException(final CoreException ex) {
		return ex.provide();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = INTERNAL_SERVER_ERROR)
	public ErrorMessage resourceNotFoundException(final Exception ex) {
		return new ErrorMessage(00000, getErrorMessages(ex));
	}

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(value = INTERNAL_SERVER_ERROR)
	public ErrorMessage resourceNotFoundException(final Throwable ex) {
		return new ErrorMessage(00000, getErrorMessages(ex));
	}

	private static String getErrorMessages(final Throwable e) {
		if (e == null) {
			return "";
		}

		Throwable t = e.getCause();
		final StringBuilder sb = new StringBuilder(e.getMessage());
		while (t != null) {
			sb.append(", ").append(t.getMessage());
			t = t.getCause();
		}
		return sb.toString();
	}
}
