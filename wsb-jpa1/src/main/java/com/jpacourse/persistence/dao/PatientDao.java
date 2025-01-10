package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {

    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime time, String description);

    List<PatientEntity> findPatientsByLastName(String lastName);

    List<PatientEntity> findPatientsByMinNumberOfVisits(long minNumberOfPatientVisits);

    List<PatientEntity> findPatientsByIsAdult(Boolean isPatientAdult);

}
