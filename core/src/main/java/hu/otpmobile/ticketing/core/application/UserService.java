package hu.otpmobile.ticketing.core.application;

import java.util.List;

import hu.otpmobile.ticketing.core.model.User;

public interface UserService {

	List<User> fetchAll();

	boolean validateToken(String token);

	boolean validateBankcardBelongsToUser(String cardId, String token);

	boolean canBankcardCover(String cardId, int price);

}
