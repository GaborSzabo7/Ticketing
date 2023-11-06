package hu.otpmobile.ticketing.core.model;

import static lombok.AccessLevel.PROTECTED;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "USERS")
public class User implements Serializable {

	private static final long serialVersionUID = -5335749834758212564L;

	@Id
	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	@Column(name = "NAME", nullable = false, length = 100)
	private String name;

	@Column(name = "EMAIL", nullable = false, length = 200)
	private String email;

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "user")
	private List<UserDevice> devices = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "user")
	private List<UserToken> tokens = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@OneToOne(mappedBy = "user")
	private UserBankcard bankcard;

	@Override
	public int hashCode() {
		return Objects.hash(email, name, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return Objects.equals(email, other.email) //
				&& Objects.equals(name, other.name) //
				&& Objects.equals(userId, other.userId);
	}

}
