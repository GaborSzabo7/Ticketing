package hu.otpmobile.ticketing.core.model;

import static lombok.AccessLevel.PROTECTED;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(staticName = "of")
@Entity
@Table(name = "USER_BANKCARD")
public class UserBankcard {

	@Id
	@Column(name = "CARD_ID", nullable = false, length = 5)
	private String cardId;

	@Column(name = "CARD_NUMBER", nullable = false)
	private Long cardNumber;

	@Column(name = "CVC", nullable = false)
	private Long cvc;

	@Column(name = "AMOUNT", nullable = false)
	private Long amount;

	@Column(name = "CURRENCY", nullable = false, length = 3)
	private String currency;

	@ToString.Exclude
	@OneToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	public boolean doesHaveThisCardId(final String cardId) {
		return this.cardId.equals(cardId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, cardId, cardNumber, currency, cvc, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UserBankcard)) {
			return false;
		}
		UserBankcard other = (UserBankcard) obj;
		return Objects.equals(amount, other.amount) //
				&& Objects.equals(cardId, other.cardId) //
				&& Objects.equals(cardNumber, other.cardNumber) //
				&& Objects.equals(currency, other.currency) //
				&& Objects.equals(cvc, other.cvc) //
				&& Objects.equals(user, other.user);
	}

}
