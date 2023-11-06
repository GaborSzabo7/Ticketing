package hu.otpmobile.ticketing.api.model;

public record Event(int eventId, String title, String location, long startTimeStamp, long endTimeStamp) {

}
