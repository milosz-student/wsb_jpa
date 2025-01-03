package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.List;
import java.util.stream.Collectors;


public class VisitMapper {
    public static VisitTO mapToTO(final VisitEntity visitEntity)
    {
        if (visitEntity == null)
        {
            return null;
        }
        final VisitTO visitTO = new VisitTO();

        visitTO.setTime(visitEntity.getTime());

        DoctorEntity doctorEntity = visitEntity.getDoctor();
        visitTO.setDoctorFirstName(doctorEntity.getFirstName());
        visitTO.setDoctorLastName(doctorEntity.getLastName());

        List<String> medicalTreatments = visitEntity.getMedicalTreatments().stream()
                        .map(t -> t.getType().name())
                        .collect(Collectors.toList());
        visitTO.setMedicalTreatments(medicalTreatments);

        return visitTO;
    }
}
