package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{
    @Override
    @Transactional
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime time, String description) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        if (patient == null) {
            throw new EntityNotFoundException("Patient not found with ID: " + patientId);
        }

        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);
        if (doctor == null) {
            throw new EntityNotFoundException("Doctor not found with ID: " + doctorId);
        }

        VisitEntity visit = new VisitEntity();
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        visit.setTime(time);
        visit.setDescription(description);

        patient.getVisits().add(visit);

        entityManager.merge(patient);
    }

    @Override
    public List<PatientEntity> findPatientsByLastName(String patientLastName) {
        return entityManager.createQuery("select patient from PatientEntity patient " +
                "where patient.lastName like :lastN", PatientEntity.class)
                .setParameter("lastN", "%"+patientLastName+"%")
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsByMinNumberOfVisits(long minNumberOfPatientVisits) {
        return entityManager.createQuery("select patient from PatientEntity patient " +
                "join patient.visits visit " +
                "group by patient " +
                "having count (visit) > :nr", PatientEntity.class)
                .setParameter("nr", minNumberOfPatientVisits)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsByIsAdult(Boolean isPatientAdult) {
        return entityManager.createQuery("select patient from PatientEntity patient " +
                "where patient.isAdult = :adult", PatientEntity.class)
                .setParameter("adult", isPatientAdult)
                .getResultList();
    }
}
