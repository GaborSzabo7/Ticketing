package hu.otpmobile.ticketing.core.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class CoreException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String USER_TOKEN_NOT_EXIST = "A felhasználói token nem szerepel.";

	private static final String USER_TOKEN_EXPIRED_OR_NOT_VALID = "A felhasználói token lejárt vagy nem értelmezhető.";

	private static final String USER_BANKCARD_NOT_MATCH = "Ez a bankkártya nem ehhez a felhasználóhoz tartozik.";

	private static final String USER_BANKCARD_NOT_HAVE_ENOUGH_AMOUNT = //
			"A felhasználónak nincs elegendő pénze hogy megvásárolja a jegyet!";

	private final int errorCode;

	public ErrorMessage provide() {
		return switch (errorCode) {
		case 10050 -> new ErrorMessage(errorCode, USER_TOKEN_NOT_EXIST);
		case 10051 -> new ErrorMessage(errorCode, USER_TOKEN_EXPIRED_OR_NOT_VALID);
		case 10100 -> new ErrorMessage(errorCode, USER_BANKCARD_NOT_MATCH);
		case 10101 -> new ErrorMessage(errorCode, USER_BANKCARD_NOT_HAVE_ENOUGH_AMOUNT);
		default -> throw new IllegalArgumentException("It is not a valid error code: " + errorCode);
		};
	}

}
