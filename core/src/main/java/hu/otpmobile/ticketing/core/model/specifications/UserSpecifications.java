package hu.otpmobile.ticketing.core.model.specifications;

import org.springframework.data.jpa.domain.Specification;

import hu.otpmobile.ticketing.core.model.User;
import hu.otpmobile.ticketing.core.model.UserToken;
import jakarta.persistence.criteria.Join;

public interface UserSpecifications {

	static Specification<User> hasTokenWith(final String token) {
		return (root, query, builder) -> {
			Join<UserToken, User> userToken = root.join("tokens");
			return builder.equal(userToken.get("token"), token);
		};
	}

}
