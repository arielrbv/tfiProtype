package com.nutritionx.portal.model;

import java.sql.Timestamp;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "patient")
public class Patient {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2",strategy = "uuid2")
	@Column(name = "patient_id")
	private String patientId;
	@Column(length = 20)
	private String dni;
	@Column (name="first_name")
	private String firstName;
	@Column (name="last_name")
	private String lastName;
	private String email;
	@Column(length = 45)
	private String phone;
	private String address;
	@Column(name="postal_code", length = 10)	
	private String postalCode;
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthdate;
	@Column(length = 45)
	private String gender;
	private float weight;
	private float height;
	@Column(name = "plan_type")
	private String planType;
	private Integer status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Column(name = "last_login")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;
	private String password;
	private String token;
	@Lob
	@Column(name = "avatar", columnDefinition="LONGBLOB")
	private byte[] avatar;
	
	
	
	/**RELATIONS WITH TABLES*/
	//WITH PROFESSIONAL
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE )
	@JoinTable(
			  name = "patient_professional", 
			  joinColumns = @JoinColumn(name = "patient_id"), 
			  inverseJoinColumns = @JoinColumn(name = "professional_id")
			  )
	Set<Professional> professional = new HashSet<>();
	
	//WITH PATIENT_NUTRIPLAN
	@OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<PatientNutriPlan> linesOfPlan = new HashSet<>();
	
	//WITH PREFERENCES BYPASSING  PATIENT_PREFERENCES
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			  name = "patient_preferences", 
			  joinColumns = @JoinColumn(name = "patient_id"), 
			  inverseJoinColumns = @JoinColumn(name = "preference_id")
			  )
	Set<Preference> preferences = new HashSet<>();
	
	//WITH PATOLOGIES BYPASSING  PATIENT_PATOLOGIES
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			  name = "patient_patologies", 
			  joinColumns = @JoinColumn(name = "patient_id"), 
			  inverseJoinColumns = @JoinColumn(name = "patology_id")
			  )
	Set<Patology> patologies = new HashSet<>();
	
	
	
	
	public Patient() {
		// TODO Auto-generated constructor stub
	}

	public Patient(String patientId, String dni, String firstName, String lastName, String email, String phone,
			String address, String postalCode, Date birthdate, String gender, float weight, float height,
			String planType, Integer status, Date created, Date lastLogin, String password, String token,
			byte[] avatar) {
		super();
		this.patientId = patientId;
		this.dni = dni;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.postalCode = postalCode;
		this.birthdate = birthdate;
		this.gender = gender;
		this.weight = weight;
		this.height = height;
		this.planType = planType;
		this.status = status;
		this.created = created;
		this.lastLogin = lastLogin;
		this.password = password;
		this.token = token;
		this.avatar = avatar;
	}




	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
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
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
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
	public byte[] getAvatar() {
		return avatar;
	}
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	public void addProfessional(Professional p) {
		this.professional.add(p);
	}
	public void addLinesOfPlan(PatientNutriPlan lineOfPlan) {
		this.linesOfPlan.add(lineOfPlan);
	}
	public void addPreference(Preference pref) {
		this.preferences.add(pref);
	}
	public void addPatology(Patology pat) {
		this.patologies.add(pat);
	}
	public void setLinesOfPlan(Set<PatientNutriPlan> linesOfPlan) {
		this.linesOfPlan = linesOfPlan;
	}
	public void setPreferences(Set<Preference> preferences) {
		this.preferences = preferences;
	}
	public void setPatologies(Set<Patology> patologies) {
		this.patologies = patologies;
	}
	public Set<PatientNutriPlan> getLinesOfPlan() {
		return linesOfPlan;
	}
	public Set<Patology> getPatologies() {
		return patologies;
	}
	public Set<Professional> getProfessional() {
		return professional;
	}
	public Set<Preference> getPreferences() {
		return preferences;
	}
	public int getAge() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());		
		return Period.between(this.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				ts.toLocalDateTime().toLocalDate()).getYears();
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", dni=" + dni + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", phone=" + phone + ", address=" + address + ", postalCode="
				+ postalCode + ", birthdate=" + birthdate + ", gender=" + gender + ", weight=" + weight + ", height="
				+ height + ", planType=" + planType + ", status=" + status + ", created=" + created + ", lastLogin="
				+ lastLogin + ", password=" + password + ", token=" + token + ", avatar=" + Arrays.toString(avatar)
				+ "]";
	}
	


	
	
	
	

}
