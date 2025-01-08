package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Specialization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest
{
    @Autowired
    private PatientDao patientDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Test
    public void testShouldFindAddressById() {
        // given
        // when
        PatientEntity patientEntity = patientDao.findOne(2L);
        // then
        assertThat(patientEntity).isNotNull();
        assertThat(patientEntity.getFirstName()).isEqualTo("Ewa");
    }

    @Transactional
    @Test
    public void testDeletePatient() {
        // given
        AddressEntity testAddress = new AddressEntity();
        testAddress.setAddressLine1("xx");
        testAddress.setAddressLine2("yy");
        testAddress.setCity("city");
        testAddress.setPostalCode("62-030");
        addressDao.save(testAddress);

        DoctorEntity testDoctor = new DoctorEntity();
        testDoctor.setFirstName("Alicja");
        testDoctor.setLastName("Madrowska");
        testDoctor.setTelephoneNumber("456123789");
        testDoctor.setDoctorNumber("D567");
        testDoctor.setEmail("alicja@example.com");
        testDoctor.setSpecialization(Specialization.SURGEON);
        testDoctor.setAddress(testAddress);

        PatientEntity testPatient = new PatientEntity();
        testPatient.setFirstName("Jan");
        testPatient.setLastName("Kowalski");
        testPatient.setTelephoneNumber("123456789");
        testPatient.setPatientNumber("P123");
        testPatient.setEmail("jan.kowal@wp.com");
        testPatient.setDateOfBirth(LocalDate.of(1985, 1, 15));
        testPatient.setIsAdult(true);

        testPatient.setAddress(testAddress);

        VisitEntity testVisit = new VisitEntity();
        testVisit.setPatient(testPatient);
        testVisit.setDoctor(testDoctor);
        testVisit.setDescription("wizyta NFZ");
        testVisit.setTime(LocalDateTime.parse("2024-11-24 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        testPatient.setVisits(List.of(testVisit));

        patientDao.save(testPatient);

        // when
        PatientEntity existingPatient = patientDao.findOne(testPatient.getId());
        assertThat(existingPatient).isNotNull();
        assertThat(existingPatient.getVisits()).isNotNull();
        assertThat(existingPatient.getVisits()).isNotEmpty();

        patientDao.delete(testPatient);

        // then
        PatientEntity deletedPatient = patientDao.findOne(testPatient.getId());
        assertThat(deletedPatient).isNull();

        PatientEntity patientWithVisits = patientDao.findOne(testPatient.getId());
        assertThat(patientWithVisits).isNull();

        assertThat(testDoctor).isNotNull();
        assertThat(testDoctor.getFirstName()).isEqualTo("Alicja");
    }

    @Transactional
    @Test
    public void testAddVisitToPatient() {
        // Given
        AddressEntity testAddress = new AddressEntity();
        testAddress.setAddressLine1("xx");
        testAddress.setAddressLine2("yy");
        testAddress.setCity("city");
        testAddress.setPostalCode("62-030");
        addressDao.save(testAddress);

        DoctorEntity doctor = new DoctorEntity();
        doctor.setAddress(testAddress);
        doctor.setFirstName("Alicja");
        doctor.setLastName("Madrowska");
        doctor.setSpecialization(Specialization.SURGEON);
        doctor.setDoctorNumber("D567");
        doctor.setTelephoneNumber("123456789");
        entityManager.persist(doctor);

        PatientEntity patient = new PatientEntity();
        patient.setAddress(testAddress);
        patient.setFirstName("Jan");
        patient.setLastName("Kowalski");
        patient.setDateOfBirth(LocalDate.of(1985, 1, 15));
        patient.setTelephoneNumber("987654321");
        patient.setPatientNumber("P123");
        patient.setIsAdult(true);
        patient.setVisits(new ArrayList<>());
        entityManager.persist(patient);

        LocalDateTime visitTime = LocalDateTime.of(2024, 11, 24, 10, 0, 0);
        String description = "wizyta NFZ";

        // When
        patientDao.addVisitToPatient(patient.getId(), doctor.getId(), visitTime, description);

        // Then
        PatientEntity updatedPatient = entityManager.find(PatientEntity.class, patient.getId());
        assertThat(updatedPatient.getVisits()).isNotEmpty();

        VisitEntity visit = updatedPatient.getVisits().stream()
                .findFirst()
                .orElseThrow(() -> new AssertionError("Visit not found"));
        assertThat(visit.getTime()).isEqualTo(visitTime);
        assertThat(visit.getDescription()).isEqualTo(description);
        assertThat(visit.getDoctor().getFirstName()).isEqualTo("Alicja");
        assertThat(visit.getDoctor().getLastName()).isEqualTo("Madrowska");
    }

    @Transactional
    @Test
    public void testFindPatientById_shouldReturn2Patients() {
        //given
        final String patientLastName = "Kowal";

        //when
        final List<PatientEntity> patients = patientDao.findPatientsByLastName(patientLastName);

        //then
        assertThat(patients).isNotEmpty();
        assertThat(patients.size()).isEqualTo(2);
        assertThat(patients.stream().sorted(Comparator.comparing(PatientEntity::getId)).collect(Collectors.toList())
                .get(0).getId()).isEqualTo(1L);
        assertThat(patients.stream().sorted(Comparator.comparing(PatientEntity::getId)).collect(Collectors.toList())
                .get(1).getId()).isEqualTo(3L);
    }

    @Transactional
    @Test
    public void testFindPatientById_shouldReturn1Patient() {
        //given
        final String patientLastName = "ab";

        //when
        final List<PatientEntity> patients = patientDao.findPatientsByLastName(patientLastName);

        //then
        assertThat(patients).isNotEmpty();
        assertThat(patients.size()).isEqualTo(1);
        assertThat(patients.get(0).getId()).isEqualTo(2L);
    }

    @Transactional
    @Test
    public void testFindPatientById_shouldReturnEmptyList() {
        //given
        final String patientLastName = "aaaaaaaaaaaaaaaaaaaaa";

        //when
        final List<PatientEntity> patients = patientDao.findPatientsByLastName(patientLastName);

        //then
        assertThat(patients).isEmpty();
    }

    @Transactional
    @Test
    public void testFindPatientsByMinNumberOfVisits_shouldReturn1Patient() {
        //given
        final long minNumberOfPatientVisits = 2;

        //when
        final List<PatientEntity> patients = patientDao.findPatientsByMinNumberOfVisits(minNumberOfPatientVisits);

        //then
        assertThat(patients).isNotEmpty();
        assertThat(patients.size()).isEqualTo(1);
        assertThat(patients.get(0).getId()).isEqualTo(2L);
    }

    @Transactional
    @Test
    public void testFindPatientsByMinNumberOfVisits_shouldReturn2Patients() {
        //given
        final long minNumberOfPatientVisits = 1;

        //when
        final List<PatientEntity> patients = patientDao.findPatientsByMinNumberOfVisits(minNumberOfPatientVisits);

        //then
        assertThat(patients).isNotEmpty();
        assertThat(patients.size()).isEqualTo(2);
        assertThat(patients.stream().sorted(Comparator.comparing(PatientEntity::getId)).collect(Collectors.toList())
                .get(0).getId()).isEqualTo(1L);
        assertThat(patients.stream().sorted(Comparator.comparing(PatientEntity::getId)).collect(Collectors.toList())
                .get(1).getId()).isEqualTo(2L);
    }

    @Transactional
    @Test
    public void testFindPatientsByMinNumberOfVisits_shouldReturnEmptyList() {
        //given
        final long minNumberOfPatientVisits = 100;

        //when
        final List<PatientEntity> patients = patientDao.findPatientsByMinNumberOfVisits(minNumberOfPatientVisits);

        //then
        assertThat(patients).isEmpty();
    }

    @Transactional
    @Test
    public void testFindPatientsByIsAdult_shouldReturn2Patients() {
        //given
        final Boolean isPatientAdult = true;

        //when
        final List<PatientEntity> patients = patientDao.findPatientsByIsAdult(isPatientAdult);

        //then
        assertThat(patients).isNotEmpty();
        assertThat(patients.size()).isEqualTo(2);
        assertThat(patients.stream().sorted(Comparator.comparing(PatientEntity::getId)).collect(Collectors.toList())
                .get(0).getId()).isEqualTo(1L);
        assertThat(patients.stream().sorted(Comparator.comparing(PatientEntity::getId)).collect(Collectors.toList())
                .get(1).getId()).isEqualTo(3L);
    }

    @Transactional
    @Test
    public void testFindPatientsByIsAdult_shouldReturn1Patient() {
        //given
        final Boolean isPatientAdult = false;

        //when
        final List<PatientEntity> patients = patientDao.findPatientsByIsAdult(isPatientAdult);

        //then
        assertThat(patients).isNotEmpty();
        assertThat(patients.size()).isEqualTo(1);
        assertThat(patients.get(0).getId()).isEqualTo(2L);
    }

}
