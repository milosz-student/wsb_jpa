package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorDaoTest {

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void testFindDoctorsEarnedMoreThan_shouldReturn1Doctor() {
        //given
        final long earnedMoreThan = 300;

        //when
        final List<DoctorEntity> doctors = doctorDao.findDoctorsEarnedMoreThan(earnedMoreThan);

        //then
        assertThat(doctors).isNotEmpty();
        assertThat(doctors.size()).isEqualTo(1);
        assertThat(doctors.get(0).getId()).isEqualTo(2L);
    }

    @Transactional
    @Test
    public void testFindDoctorsEarnedMoreThan_shouldReturn2Doctors() {
        //given
        final long earnedMoreThan = 299;

        //when
        final List<DoctorEntity> doctors = doctorDao.findDoctorsEarnedMoreThan(earnedMoreThan);

        //then
        assertThat(doctors).isNotEmpty();
        assertThat(doctors.size()).isEqualTo(2);
        assertThat(doctors.get(0).getId()).isEqualTo(1L);
        assertThat(doctors.get(1).getId()).isEqualTo(2L);
    }

    @Transactional
    @Test
    public void testFindDoctorsEarnedMoreThan_shouldReturnEmptyList() {
        //given
        final long earnedMoreThan = 600;

        //when
        final List<DoctorEntity> doctors = doctorDao.findDoctorsEarnedMoreThan(earnedMoreThan);

        //then
        assertThat(doctors).isEmpty();
    }

}
