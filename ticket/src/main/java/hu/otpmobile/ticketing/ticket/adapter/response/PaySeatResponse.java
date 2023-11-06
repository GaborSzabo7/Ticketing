package hu.otpmobile.ticketing.ticket.adapter.response;

import java.security.SecureRandom;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

public abstract class PaySeatResponse {

	public static PaySeatSuccessResponse buildSuccessResponse() {
		return new PaySeatSuccessResponse(true, generateRandom());
	}

	public static PaySeatFailedResponse buildFailedResponse(final long errorCode) {
		return new PaySeatFailedResponse(false, errorCode);
	}

	private static long generateRandom() {
		return new SecureRandom().nextLong(0, Long.MAX_VALUE);
	}

	public abstract boolean isSuccess();

	public abstract long getCode();

	@AllArgsConstructor
	static class PaySeatSuccessResponse extends PaySeatResponse {

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

		@Override
		public String toString() {
			return "(success: " + success + ", reservationId: " + reservationId + ")";
		}

	}

	@AllArgsConstructor
	static class PaySeatFailedResponse extends PaySeatResponse {

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

		@Override
		public String toString() {
			return "(success: " + success + ", errorCode: " + errorCode + ")";
		}

	}

}
