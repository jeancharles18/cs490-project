package mum.pmp.mstore.model;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
@Table(name = "PROFILE")
public class Profile {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private long id;
	
	@Column(name="FIRST_NAME", length=20)
	private String firstName;
	
	@Column(name="LAST_NAME", length=20)
	private String lastName;
	
	@Column(name = "EMAIL", unique = true)
	private String email;
	
	@Column(name = "PHONE", nullable=false)
	private String phone;
	
	//need to remove 
	@Column(name = "ENABLE")
	private boolean enable;
	
	@Transient
	private String password;
	
	private String token;
	
	@Transient
	private String confirmPassword;
	
	// By default user is enable. But user can
	// decide to disable himself.
	// 1: Enable
	// 2: Disable.
	private byte status = 1;

	@OneToOne(cascade = CascadeType.ALL)
	private CreditCard creditCard;
	
	
	public long getId() {
		return id;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", enable=" + enable + ", password=" + password + ", token=" + token
				+ ", confirmPassword=" + confirmPassword + ", status=" + status + "]";
	}

	
	
	
}
