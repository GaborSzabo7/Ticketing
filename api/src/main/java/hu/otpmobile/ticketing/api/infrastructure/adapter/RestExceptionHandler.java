package hu.otpmobile.ticketing.api.infrastructure.adapter;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import hu.otpmobile.ticketing.api.infrastructure.exception.ApiException;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ApiException.class)
	@ResponseStatus(value = INTERNAL_SERVER_ERROR)
	public String coreException(final ApiException ex) {
		return ex.getMessage();
	}

}
