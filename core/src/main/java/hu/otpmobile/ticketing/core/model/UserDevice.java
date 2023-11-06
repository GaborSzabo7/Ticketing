package hu.otpmobile.ticketing.core.model;

import static lombok.AccessLevel.PROTECTED;

import java.io.Serializable;
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
@Table(name = "USER_DEVICE")
public class UserDevice implements Serializable {

	private static final long serialVersionUID = 8990765841229712556L;

	@Id
	@Column(name = "DEVICE_HASH", nullable = false, length = 32)
	private String deviceHash;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@Override
	public int hashCode() {
		return Objects.hash(deviceHash, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UserDevice)) {
			return false;
		}
		UserDevice other = (UserDevice) obj;
		return Objects.equals(deviceHash, other.deviceHash) //
				&& Objects.equals(user, other.user);
	}

}
