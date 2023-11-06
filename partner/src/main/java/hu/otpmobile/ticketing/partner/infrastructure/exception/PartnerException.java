package hu.otpmobile.ticketing.partner.infrastructure.exception;

import lombok.Getter;

@Getter
public class PartnerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String NO_SUCH_EVENT = "Nem létezik ilyen esemémny!";
	private static final String NO_SUCH_SEAT = "Nem létezik ilyen szék!";
	private static final String SEAT_ALREADY_RESERVED = "Már lefoglalt székre nem lehet jegyet eladni!";

	private final int errorCode;

	public PartnerException(final int errorCode) {
		super(provideErrorMessage(errorCode));
		this.errorCode = errorCode;
	}

	private static String provideErrorMessage(final int errorCode) {
		return switch (errorCode) {
		case 90001 -> NO_SUCH_EVENT;
		case 90002 -> NO_SUCH_SEAT;
		case 90010 -> SEAT_ALREADY_RESERVED;
		default -> throw new IllegalArgumentException("No such errorCode: " + errorCode);
		};
	}

}
