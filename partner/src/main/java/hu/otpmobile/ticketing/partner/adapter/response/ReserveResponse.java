package hu.otpmobile.ticketing.partner.adapter.response;

import java.security.SecureRandom;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

public abstract class ReserveResponse {

	public static ReserveResponse successResponse() {
		return new ReserveSuccessResponse(true, generateRandom());
	}

	public static ReserveResponse errorResponse(final long errorCode) {
		return new ReserveFailureResponse(false, errorCode);
	}

	private static long generateRandom() {
		return new SecureRandom().nextLong(0, Long.MAX_VALUE);
	}

	public abstract boolean isSuccess();

	public abstract long getCode();

	@AllArgsConstructor
	static final class ReserveSuccessResponse extends ReserveResponse {

		private final boolean success;

		private final long reservationId;

		@Override
		public boolean isSuccess() {
			return success;
		}

		@JsonProperty("reservationId")
		@Override
		public long getCode() {
			return reservationId;
		}

	}

	@AllArgsConstructor
	static final class ReserveFailureResponse extends ReserveResponse {

		private final boolean success;

		private final long errorCode;

		@Override
		public boolean isSuccess() {
			return success;
		}

		@JsonProperty("errorCode")
		@Override
		public long getCode() {
			return errorCode;
		}

	}

}
