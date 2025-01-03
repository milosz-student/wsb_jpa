package com.jpacourse.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class VisitTO implements Serializable {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    private List<String> medicalTreatments;

    private String doctorFirstName;

    private String doctorLastName;


    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setMedicalTreatments(List<String> medicalTreatments) {
        this.medicalTreatments = medicalTreatments;
    }

    public List<String> getMedicalTreatments() { return medicalTreatments; }

    public void setDoctorFirstName(String doctorFirstName) { this.doctorFirstName = doctorFirstName; }

    public void setDoctorLastName(String doctorLastName) { this.doctorLastName = doctorLastName; }

    public String getDoctorFirstName() { return doctorFirstName; }

    public String getDoctorLastName() { return doctorLastName; }

}
