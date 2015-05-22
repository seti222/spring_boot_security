package pe.seti222.domain;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "userid", nullable = false, unique = true)
    private String userId;

    @Column(name = "ext_auth", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExtAuth extAuth = ExtAuth.SITE;
    
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public Long getId() {
        return id;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    
    public ExtAuth getExtAuth() {
		return extAuth;
	}

	public void setExtAuth(ExtAuth extAuth) {
		this.extAuth = extAuth;
	}

	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId+
                ", passwordHash='" + passwordHash.substring(0, 10) +
                ", role=" + role +
                '}';
    }
}
