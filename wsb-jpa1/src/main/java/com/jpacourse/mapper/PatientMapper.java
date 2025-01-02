package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.PatientEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PatientMapper {
    public static PatientTO mapToTO(final PatientEntity patientEntity)
    {
        if (patientEntity == null)
        {
            return null;
        }

        final PatientTO patientTO = new PatientTO();

        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setDateOfBirth(patientEntity.getDateOfBirth());
        patientTO.setAddress(patientEntity.getAddress());

        if (patientEntity.getVisits() != null) {
            List<VisitTO> visitsTo = patientEntity.getVisits().stream()
                    .map(VisitMapper::mapToTO)
                    .collect(Collectors.toList());
            patientTO.setVisits(visitsTo);
        }

        patientTO.setIsAdult(patientEntity.getIsAdult());

        return patientTO;
    }

    public static PatientEntity mapToEntity(final PatientTO patientTO)
    {
        if (patientTO == null)
        {
            return null;
        }

        PatientEntity patientEntity = new PatientEntity();

        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setEmail(patientTO.getEmail());
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        patientEntity.setAddress(patientTO.getAddress());
        patientEntity.setIsAdult(patientTO.getIsAdult());

        return patientEntity;
    }
}
