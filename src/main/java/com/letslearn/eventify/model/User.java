package com.letslearn.eventify.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "User",
indexes = {@Index(name = "email_index",  columnList="email", unique = true),
        @Index(name = "mobile_index", columnList="mobile_number", unique = true),
        @Index(name = "username_index", columnList="user_name", unique = true)})
public class User implements UserDetails{
	
	private static final long serialVersionUID = 7637238970851521842L;

	@Id
	@Column(name="id",unique=true)
	@Type(type="org.hibernate.type.UUIDCharType")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
	
	@Column(name="email",unique=true)
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	@Column(name="user_name",unique=true)
	private String userName;
	
	@Column(name="mobile_number",unique=true)
	private String mobileNumber;
	
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private Set<Role> roles = new HashSet<>();
	
	@Column(name = "account_non_expired", columnDefinition="tinyint(1) default 1")
	private boolean accountNonExpired;

	@Column(name = "account_non_locked", columnDefinition="tinyint(1) default 1")
	private boolean accountNonLocked;

	@Column(name = "credentials_non_expired", columnDefinition="tinyint(1) default 1")
	private boolean credentialsNonExpired;

	@Column(name = "enabled", columnDefinition="tinyint(1) default 1")
	private boolean enabled;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        
        for (Role role : this.roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {

		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	

}
