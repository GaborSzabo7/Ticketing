package hu.otpmobile.ticketing.core.application;

import static hu.otpmobile.ticketing.core.infrastructure.util.StreamUtil.sequentialStream;
import static hu.otpmobile.ticketing.core.model.specifications.UserSpecifications.hasTokenWith;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.otpmobile.ticketing.core.model.User;
import hu.otpmobile.ticketing.core.model.UserRepository;
import hu.otpmobile.ticketing.core.model.specifications.UserBankcardRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserBankcardRepository userBankcardRepository;

	public UserServiceImpl(final UserRepository userRepository, final UserBankcardRepository userBankcardRepository) {
		requireNonNull(userRepository, "userRepository can't be null");
		requireNonNull(userBankcardRepository, "userBankcardRepository can't be null");
		this.userRepository = userRepository;
		this.userBankcardRepository = userBankcardRepository;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = REQUIRES_NEW, readOnly = true)
	public List<User> fetchAll() {
		log.debug("fetch available users");
		return sequentialStream(userRepository.findAll()).collect(toList());
	}

	@Override
	@Transactional(value = "transactionManager", propagation = REQUIRES_NEW, readOnly = true)
	public boolean validateToken(final String token) {
		log.debug("validate user token: {}", token);
		if (isBlank(token)) {
			return false;
		}

		return userRepository.findOne(hasTokenWith(token)).isPresent();
	}

	@Override
	@Transactional(value = "transactionManager", propagation = REQUIRES_NEW, readOnly = true)
	public boolean validateBankcardBelongsToUser(final String cardId, final String token) {
		return userRepository.findOne(hasTokenWith(token)) //
				.filter(u -> u.getBankcard().doesHaveThisCardId(cardId)) //
				.isPresent();
	}

	@Override
	@Transactional(value = "transactionManager", propagation = REQUIRES_NEW, readOnly = true)
	public boolean canBankcardCover(String cardId, int price) {
		log.debug("can bankcard({}) cover price({}) based on amount", cardId, price);
		return userBankcardRepository.findById(cardId).filter(c -> c.getAmount() >= price).isPresent();
	}

}
