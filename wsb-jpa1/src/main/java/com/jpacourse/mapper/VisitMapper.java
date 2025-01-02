package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.VisitEntity;


public class VisitMapper {
    public static VisitTO mapToTO(final VisitEntity visitEntity)
    {
        if (visitEntity == null)
        {
            return null;
        }
        final VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());
        visitTO.setMedicalTreatments(visitEntity.getMedicalTreatments());
        visitTO.setDoctor(visitEntity.getDoctor());
        visitTO.setPatient(visitEntity.getPatient());

        return visitTO;
    }
}
