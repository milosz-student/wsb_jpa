package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.DoctorEntity;

import java.util.List;

public interface DoctorDao extends Dao<DoctorEntity, Long> {

    List<DoctorEntity> findDoctorsEarnedMoreThan(long x);

}
