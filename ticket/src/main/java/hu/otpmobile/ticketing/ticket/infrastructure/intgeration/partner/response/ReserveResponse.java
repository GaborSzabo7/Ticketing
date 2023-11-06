package hu.otpmobile.ticketing.ticket.infrastructure.intgeration.partner.response;

import java.security.SecureRandom;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import hu.otpmobile.ticketing.ticket.infrastructure.intgeration.partner.response.ReserveResponse.ReserveFailureResponse;
import hu.otpmobile.ticketing.ticket.infrastructure.intgeration.partner.response.ReserveResponse.ReserveSuccessResponse;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({ //
		@JsonSubTypes.Type(ReserveSuccessResponse.class), //
		@JsonSubTypes.Type(ReserveFailureResponse.class) //
})
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

	static final class ReserveSuccessResponse extends ReserveResponse {

		private final boolean success;

		private final long reservationId;

		@JsonCreator
		public ReserveSuccessResponse( //
				@JsonProperty("success") boolean success, //
				@JsonProperty("reservationId") long reservationId) {
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

	static final class ReserveFailureResponse extends ReserveResponse {

		private final boolean success;

		private final long errorCode;

		@JsonCreator
		public ReserveFailureResponse( //
				@JsonProperty("success") boolean success, //
				@JsonProperty("errorCode") long errorCode) {
			super();
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
