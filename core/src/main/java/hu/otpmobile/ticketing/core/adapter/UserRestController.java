package hu.otpmobile.ticketing.core.adapter;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.otpmobile.ticketing.core.application.UserService;
import hu.otpmobile.ticketing.core.infrastructure.adapter.BaseRestController;
import hu.otpmobile.ticketing.core.model.User;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/user")
public class UserRestController extends BaseRestController {

	private final UserService userService;

	public UserRestController(final UserService userService) {
		requireNonNull(userService, "userService can't be null");
		this.userService = userService;
	}

	@Operation(summary = "Fetch available users")
	@GetMapping("/fetch/all")
	public Callable<ResponseEntity<List<User>>> fetchUsers() {
		return get(() -> userService.fetchAll());
	}

	@Operation(summary = "Validate given token")
	@PostMapping("/validate/{token}")
	public Callable<ResponseEntity<Boolean>> validateToken(@PathVariable final String token) {
		return post(() -> userService.validateToken(token));
	}

	@Operation(summary = "Validate bankcard of given user")
	@PostMapping("/validate/card/{cardId}/{token}")
	public Callable<ResponseEntity<Boolean>> validateBankcardBelongsToUser( //
			@PathVariable final String cardId, //
			@PathVariable final String token) {
		return post(() -> userService.validateBankcardBelongsToUser(cardId, token));
	}

	@Operation(summary = "Validate card has enough money to pay price")
	@PostMapping("/validate/cover/{cardId}/{price}")
	public Callable<ResponseEntity<Boolean>> canBankcardCover(@PathVariable final String cardId, @PathVariable final int price) {
		return post(() -> userService.canBankcardCover(cardId, price));
	}

}
