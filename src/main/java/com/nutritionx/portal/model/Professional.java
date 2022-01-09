package com.nutritionx.portal.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="professional")
public class Professional {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2",strategy = "uuid2")
	@Column(name = "professional_id")
	private String professionalId;
	@Column(length = 20)
	private String dni;
	@Column (name="license_id")
	private String licenseId;
	@Column (name="first_name")
	private String firstName;
	@Column (name="last_name")
	private String lastName;
	private char gender;
	private String email;
	@Column(length = 45)
	private String phone;
	private String address;
	@Column(name="postal_code", length = 10)	
	private String postalCode;
	private Integer status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Column(name = "last_login")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;
	private String password;
	private String token;
	
	
	//RELATIONS WITH TABLES
	@ManyToMany(mappedBy = "professional", cascade = CascadeType.ALL)
	private Set<Patient> patients = new HashSet<>();
	
	
	public Professional() {
		// TODO Auto-generated constructor stub
	}
	public Professional(String professionalId, String dni, String licenseId, String firstName, String lastName,
			String email, String phone, String address, String postalCode, Integer status, Date created, Date lastLogin,
			String password, String token) {
		this.professionalId = professionalId;
		this.dni = dni;
		this.licenseId = licenseId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.postalCode = postalCode;
		this.status = status;
		this.created = created;
		this.lastLogin = lastLogin;
		this.password = password;
		this.token = token;
	}
	
	
	
	public String getProfessionalId() {
		return professionalId;
	}
	public void setProfessionalId(String professionalId) {
		this.professionalId = professionalId;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getLicenseId() {
		return licenseId;
	}
	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
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
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
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
	public void addPatient(Patient p) {
		this.patients.add(p);
	}
	public Set<Patient> getPatients() {
		return patients;
	}
	
	
	
	@Override
	public String toString() {
		return "Professional [professionalId=" + professionalId + ", dni=" + dni + ", licenseId=" + licenseId
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", postalCode=" + postalCode + ", status=" + status
				+ ", created=" + created + ", lastLogin=" + lastLogin + ", password=" + password + ", token=" + token + "]";
	}
	
	

	
	
}
