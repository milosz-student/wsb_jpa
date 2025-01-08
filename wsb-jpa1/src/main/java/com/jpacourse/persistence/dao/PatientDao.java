package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {

    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime time, String description);

    public List<PatientEntity> findPatientsByLastName(String lastName);

    public List<PatientEntity> findPatientsByMinNumberOfVisits(long minNumberOfPatientVisits);

    public List<PatientEntity> findPatientsByIsAdult(Boolean isPatientAdult);

}
