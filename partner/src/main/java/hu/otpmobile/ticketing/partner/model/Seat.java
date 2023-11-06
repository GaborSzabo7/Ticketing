package hu.otpmobile.ticketing.partner.model;

public record Seat(String id, int price, String currency, boolean reserved) {

	public boolean canAffordSeat(int price) {
		return this.price < price;
	}

}
