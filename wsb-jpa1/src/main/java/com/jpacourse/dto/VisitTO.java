package com.jpacourse.dto;

import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.PatientEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class VisitTO {
    private Long id;

    private String description;

    private LocalDateTime time;

    private List<MedicalTreatmentEntity> medicalTreatments;

    private DoctorEntity doctor;

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
}
