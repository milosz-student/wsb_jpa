package com.jpacourse.persistence.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "PATIENT")
public class PatientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String telephoneNumber;

	private String email;

	@Column(nullable = false)
	private String patientNumber;

	@Column(nullable = false)
	private LocalDate dateOfBirth;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // relacja jednokierunkowa -> 1 do 1 od strony rodzica
	@JoinColumn(name = "ADDRESS_ID", nullable = false) // klucz obcy w encji nadrzednej czyli u pacjenta
	private AddressEntity address;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // relacja dwukierunkowa 1 do wielu od strony rodzica
	private List<VisitEntity> visits;

	@Column
	private Boolean isAdult;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPatientNumber() {
		return patientNumber;
	}

	public void setPatientNumber(String patientNumber) {
		this.patientNumber = patientNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public AddressEntity getAddress() { return address; }

	public void setAddress(AddressEntity address) {this.address = address;}

	public List<VisitEntity> getVisits() { return visits; }

	public Boolean getIsAdult() { return isAdult; }

	public void setIsAdult(Boolean isAdult) { this.isAdult = isAdult; }

	public void setVisits(List<VisitEntity> visits) { this.visits = visits; }

}
