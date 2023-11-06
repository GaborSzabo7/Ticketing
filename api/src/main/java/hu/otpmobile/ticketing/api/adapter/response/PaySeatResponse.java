package hu.otpmobile.ticketing.api.adapter.response;

import java.security.SecureRandom;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import hu.otpmobile.ticketing.api.adapter.response.PaySeatResponse.PaySeatFailedResponse;
import hu.otpmobile.ticketing.api.adapter.response.PaySeatResponse.PaySeatSuccessResponse;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({ //
		@JsonSubTypes.Type(PaySeatSuccessResponse.class), //
		@JsonSubTypes.Type(PaySeatFailedResponse.class) //
})
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

	static class PaySeatSuccessResponse extends PaySeatResponse {

		private final boolean success;

		private final long reservationId;

		@JsonCreator
		public PaySeatSuccessResponse( //
				@JsonProperty("success") final boolean success, //
				@JsonProperty("reservationId") final long reservationId) {
			this.success = success;
			this.reservationId = reservationId;
		}

		@Override
		public boolean isSuccess() {
			return success;
		}

		@Override
		public long getCode() {
			return reservationId;
		}

		@Override
		public String toString() {
			return "(success: " + success + ", reservationId: " + reservationId + ")";
		}

	}

	static class PaySeatFailedResponse extends PaySeatResponse {

		private final boolean success;

		private final long errorCode;

		@JsonCreator
		public PaySeatFailedResponse( //
				@JsonProperty("success") final boolean success, //
				@JsonProperty("errorCode") final long errorCode) {
			this.success = success;
			this.errorCode = errorCode;
		}

		@Override
		public boolean isSuccess() {
			return success;
		}

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
