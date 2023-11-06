package hu.otpmobile.ticketing.partner.model;

import java.util.List;
import java.util.Optional;

public record Seats(int eventId, List<Seat> seats) {

	public Optional<Seat> tryFindSeat(final long seatNumber) {
		return seats.stream() //
				.filter(s -> s.id().equals("S" + seatNumber)) //
				.findFirst();
	}

	public Optional<Seat> tryReserveSeat(final long seatNumber) {
		return seats.stream() //
				.filter(s -> s.id().equals("S" + seatNumber)) //
				.findFirst() //
				.filter(s -> s.reserved() == false);
	}

	public int getPrice(final long seatNumber) {
		return seats.stream() //
				.filter(s -> s.id().equals("S" + seatNumber)) //
				.findFirst() //
				.map(Seat::price) //
				.orElseThrow(() -> new IllegalStateException("Seat price can't be determined"));
	}

}
