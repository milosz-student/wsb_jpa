package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.*;
import com.jpacourse.persistence.enums.Specialization;
import com.jpacourse.persistence.enums.TreatmentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientDao patientDao;

    @Test
    @Transactional
    public void testFindPatientById() {
        // given
        AddressEntity testAddress = new AddressEntity();
        testAddress.setAddressLine1("xx");
        testAddress.setAddressLine2("yy");
        testAddress.setCity("city");
        testAddress.setPostalCode("62-030");

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

        MedicalTreatmentEntity testTreatment = new MedicalTreatmentEntity();
        testTreatment.setDescription("test1");
        testTreatment.setType(TreatmentType.USG);

        testVisit.setTreatments(List.of(testTreatment));
        testPatient.setVisits(List.of(testVisit));

        patientDao.save(testPatient);

        // when
        PatientTO patientTO = patientService.findById(testPatient.getId());

        // then
        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getFirstName()).isEqualTo("Jan");
        assertThat(patientTO.getLastName()).isEqualTo("Kowalski");
        assertThat(patientTO.getTelephoneNumber()).isEqualTo("123456789");
        assertThat(patientTO.getAddress().getAddressLine1()).isEqualTo("xx");
        assertThat(patientTO.getAddress().getAddressLine2()).isEqualTo("yy");
        assertThat(patientTO.getAddress().getCity()).isEqualTo("city");
        assertThat(patientTO.getAddress().getPostalCode()).isEqualTo("62-030");
        assertThat(patientTO.getEmail()).isEqualTo("jan.kowal@wp.com");
        assertThat(patientTO.getPatientNumber()).isEqualTo("P123");
        assertThat(patientTO.getDateOfBirth().isEqual(LocalDate.of(1985, 1, 15)));
        assertThat(patientTO.getIsAdult()).isEqualTo(true);
        assertThat(patientTO.getVisits()).isNotEmpty();

        patientTO.getVisits().forEach(visit -> {
            assertThat(visit.getTime()).isEqualTo(LocalDateTime.of(2024, 11, 24, 10, 0, 0));
            assertThat(visit.getDoctorFirstName()).isEqualTo("Alicja");
            assertThat(visit.getDoctorLastName()).isEqualTo("Madrowska");
            assertThat(visit.getMedicalTreatments()).contains("USG");
        });
    }
}
