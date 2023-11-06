package hu.otpmobile.ticketing.core.model;

import static lombok.AccessLevel.PROTECTED;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "USER_TOKEN")
public class UserToken {

	@Id
	@Column(name = "TOKEN", nullable = false, length = 32)
	private String token;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@Override
	public int hashCode() {
		return Objects.hash(token, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UserToken)) {
			return false;
		}
		UserToken other = (UserToken) obj;
		return Objects.equals(token, other.token) //
				&& Objects.equals(user, other.user);
	}

}
