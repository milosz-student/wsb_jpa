package com.jpacourse.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(nullable = false)
	private LocalDateTime time;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // relacja jednostronna -> 1 do wielu od strony rodzica
	@JoinColumn(name = "VISIT_ID")  // klucz obcy w encji podrzednej czyli w medical treatment
	private List<MedicalTreatmentEntity> medicalTreatments;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCTOR_ID", nullable = false)
	private DoctorEntity doctor;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "PATIENT_ID", nullable = false)
	private PatientEntity patient;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public List<MedicalTreatmentEntity> getMedicalTreatments() { return medicalTreatments; }

	public void setMedicalTreatments(List<MedicalTreatmentEntity> medicalTreatments) {
		this.medicalTreatments = medicalTreatments;
	}

	public DoctorEntity getDoctor() { return doctor; }

	public void setDoctor(DoctorEntity doctor) { this.doctor = doctor; }

	public PatientEntity getPatient() { return patient; }

	public void setPatient(PatientEntity patient) { this.patient = patient; }

}
