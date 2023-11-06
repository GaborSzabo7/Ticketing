package hu.otpmobile.ticketing.api.adapter.response;

import java.util.List;

import hu.otpmobile.ticketing.api.model.Event;

public record EventData(List<Event> data, boolean success) {

}
