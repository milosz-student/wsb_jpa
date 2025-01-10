package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao
{
    @Override
    public List<DoctorEntity> findDoctorsEarnedMoreThan(long x) {
        return entityManager.createQuery("select doctor from DoctorEntity doctor " +
                "join doctor.visits visit " +
                "group by doctor " +
                "having sum(visit.price) > :paramx", DoctorEntity.class)
                .setParameter("paramx", x)
                .getResultList();
    }
}
